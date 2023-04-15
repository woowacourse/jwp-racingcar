package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarsDao;
import racingcar.dao.PlayRecordsDao;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.PlayResponseDto;
import racingcar.dto.PlayResponseDtoConverter;

@Service
public class RacingCarService {

    private final PlayRecordsDao gameDao;
    private final CarsDao carsDao;
    private final RacingGame racingGame;

    public RacingCarService(final PlayRecordsDao gameDao, final CarsDao carsDao, final RacingGame racingGame) {
        this.gameDao = gameDao;
        this.carsDao = carsDao;
        this.racingGame = racingGame;
    }

    // TODO Transactional 사용법, 적용 범위 등 학습
    @Transactional
    public PlayResponseDto playGame(int count, List<String> carNames) {
        List<CarDto> racedCars = racingGame.play(count, carNames);
        saveGame(count, racedCars);
        return PlayResponseDtoConverter.of(racedCars);
    }

    private void saveGame(int count, List<CarDto> cars) {
        long savedId = gameDao.insertAndReturnId(count);
        carsDao.insert(savedId, cars);
    }

    public List<PlayResponseDto> findAllGames() {
        Map<Long, List<CarDto>> allCars = carsDao.findAllCarsById();
        return allCars.values()
                .stream()
                .map(PlayResponseDtoConverter::of)
                .collect(Collectors.toList());
    }

}
