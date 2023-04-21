package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.RacingCarNames;
import racingcar.domain.RacingCars;
import racingcar.domain.RacingGame;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.PlayResponseDtoConverter;
import racingcar.repository.dao.CarsDao;
import racingcar.repository.dao.PlayRecordsDao;
import racingcar.repository.dao.entity.EntityConverter;
import racingcar.repository.dao.entity.PlayRecordEntity;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class RacingCarService {

    private final PlayRecordsDao playRecordsDao;
    private final CarsDao carsDao;

    @Transactional
    public PlayResponseDto playGame(final int count, final List<String> carNames) {
        playRecordsDao.insert(new PlayRecordEntity(count));
        final Long playRecordId = playRecordsDao.getLastId();
        final RacingGame racingGame = RacingGame.of(new RacingCarNames(carNames).createCars(playRecordId));

        racingGame.play(count);

        final List<Car> cars = racingGame.racingCars();
        carsDao.insert(EntityConverter.toDaoEntities(cars));
        return PlayResponseDtoConverter.from(cars, racingGame.findWinningCarNames());
    }

    public List<PlayResponseDto> findAllPlayRecords() {
        return carsDao.findAllCarsOrderByPlayCreatedAtDesc()
                .stream()
                .map(EntityConverter::toDomainEntities)
                .map(racingCars -> PlayResponseDtoConverter.from(
                        racingCars, new RacingCars(racingCars).findWinningCarNames()))
                .collect(Collectors.toList());
    }
}
