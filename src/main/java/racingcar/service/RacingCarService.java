package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarsDao;
import racingcar.dao.PlayRecordsDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.EntityConverter;
import racingcar.domain.Car;
import racingcar.domain.RacingCarNames;
import racingcar.domain.RacingGame;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.PlayResponseDtoConverter;

@Service
@Transactional(readOnly = true)
public class RacingCarService {

    private final PlayRecordsDao playRecordsDao;
    private final CarsDao carsDao;

    public RacingCarService(final PlayRecordsDao playRecordsDao, final CarsDao carsDao) {
        this.playRecordsDao = playRecordsDao;
        this.carsDao = carsDao;
    }

    @Transactional
    public PlayResponseDto playGame(final int count, final RacingCarNames carNames) {
        RacingGame racingGame = RacingGame.of(carNames.createCars());
        racingGame.play(count);
        List<Car> cars = racingGame.racingCars();
        saveGame(count, cars);
        return PlayResponseDtoConverter.from(cars, racingGame.findWinningCarNames());
    }

    private void saveGame(final int count, final List<Car> cars) {
        playRecordsDao.insert(count);
        long savedId = playRecordsDao.getLastId();
        carsDao.insert(savedId, EntityConverter.toDaoEntities(cars));
    }

    public List<PlayResponseDto> findAllPlayRecords() {
        List<CarEntity> allCars = carsDao.findAllCarsByPlayId();
        // TODO 이렇게 map 으로 꺼내는 방법 뿐일까? repository 클래스로 책임 분리?
        Map<Long, List<Car>> carsById = allCars.stream()
                .map(EntityConverter::toDomainEntity)
                .collect(Collectors.groupingBy(Car::getPlayId));
        return carsById.values()
                .stream()
                .map(RacingGame::of)
                .map(racingGame ->
                        PlayResponseDtoConverter.from(racingGame.racingCars(), racingGame.findWinningCarNames()))
                .collect(Collectors.toList());
    }
}
