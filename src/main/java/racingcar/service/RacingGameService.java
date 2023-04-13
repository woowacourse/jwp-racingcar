package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.*;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.dto.CarDto;
import racingcar.dto.GameDto;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RacingGameService {

    private static final int RACE_START_POINT = 0;
    private static final String DELIMITER = ",";

    private final GameDao gameDao;
    private final CarDao carDao;
    private final NumberGenerator numberGenerator;

    public RacingGameService(final GameDao gameDao, final CarDao carDao, final NumberGenerator numberGenerator) {
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingGameResponseDto run(RacingGameRequestDto racingGameRequestDto) {
        RacingCars racingCars = createRacingCars(racingGameRequestDto);
        int tryCount = new TryCount(racingGameRequestDto.getCount()).getTries();
        raceCars(racingCars, tryCount);

        int gameId = saveGame(racingCars, tryCount);
        saveCars(racingCars, gameId);

        return createResult(racingCars);
    }

    private void saveCars(final RacingCars racingCars, final int gameId) {
        for (Car car : racingCars.getCars()) {
            CarDto carDto = new CarDto(car.getName(), car.getCurrentPosition(), gameId);
            carDao.save(carDto);
        }
    }

    private RacingCars createRacingCars(final RacingGameRequestDto racingGameRequestDto) {
        List<String> carNames = List.of(racingGameRequestDto.getNames().split(DELIMITER));
        List<Car> cars = CarFactory.generate(carNames, RACE_START_POINT);
        return new RacingCars(cars);
    }

    private void raceCars(final RacingCars racingCars, final int tryCount) {
        IntStream.range(0, tryCount)
                .forEach(ignored -> racingCars.moveCars(numberGenerator));
    }

    private int saveGame(final RacingCars racingCars, final int tryCount) {
        String winnerCars = String.join(",", racingCars.pickWinnerCarNames());
        return gameDao.save(new GameDto(winnerCars, tryCount, LocalTime.now()));
    }

    private RacingGameResponseDto createResult(final RacingCars racingCars) {
        List<String> winnerCars = racingCars.pickWinnerCarNames();
        List<CarStatusDto> carStatuses = racingCars.getCars().stream()
                .map(car -> new CarStatusDto(car.getName(), car.getCurrentPosition()))
                .collect(Collectors.toUnmodifiableList());

        return new RacingGameResponseDto(winnerCars, carStatuses);
    }
}
