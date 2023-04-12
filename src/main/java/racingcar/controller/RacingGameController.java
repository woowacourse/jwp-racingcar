package racingcar.controller;

import java.util.InputMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.GameResultDao;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;
import racingcar.dto.StartGameRequestDto;
import racingcar.utils.CarsFactory;
import racingcar.utils.RandomPowerGenerator;
import racingcar.utils.RandomPowerMaker;

@RestController
public class RacingGameController {

    private final RandomPowerGenerator randomPowerGenerator;
    private final GameResultDao gameResultDao;

    public RacingGameController(final GameResultDao gameResultDao) {
        this.randomPowerGenerator = new RandomPowerMaker();
        this.gameResultDao = gameResultDao;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResultResponseDto> startGame(@RequestBody final StartGameRequestDto request) {
        Cars cars = getCars(request.getNames());
        TryCount tryCount = getTryCount(request.getCount());

        GameResultResponseDto gameResult = startRace(cars, tryCount);
        gameResultDao.save(tryCount, gameResult);

        return new ResponseEntity<>(gameResult, HttpStatus.OK);
    }

    private Cars getCars(final String input) {
        try {
            String[] carNames = input.split(",");
            return CarsFactory.createCars(carNames);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    private TryCount getTryCount(final int input) {
        try {
            return new TryCount(input);
        } catch (IllegalArgumentException | InputMismatchException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    private GameResultResponseDto startRace(final Cars cars, final TryCount tryCount) {
        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
        }

        return GameResultResponseDto.toDto(cars.getWinnerNames(), cars);
    }
}
