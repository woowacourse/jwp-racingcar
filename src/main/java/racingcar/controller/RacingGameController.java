package racingcar.controller;

import java.util.InputMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.console.InputView;
import racingcar.console.OutputView;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.StartGameRequestDto;
import racingcar.utils.CarsFactory;
import racingcar.utils.RandomPowerGenerator;
import racingcar.utils.RandomPowerMaker;

@RestController
public class RacingGameController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RandomPowerGenerator randomPowerGenerator = new RandomPowerMaker();

    @PostMapping("/plays")
    public ResponseEntity<?> startGame(@RequestBody StartGameRequestDto request) {
        Cars cars = getCars(request.getNames());
        TryCount tryCount = getTryCount(request.getCount());

        startRace(cars, tryCount);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    private Cars getCars(final String input) {
        String[] carNames = input.split(",");

        try {
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

    private void startRace(Cars cars, TryCount tryCount) {
        outputView.printResultMessage();

        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
            outputView.printCurrentRacingStatus(cars);
        }
    }
}
