package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.RacingCarDto;
import racingcar.utils.Parser;
import racingcar.validator.Validator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingConsoleController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    public void run() {
        List<String> carNames = getValidCarNames();
        int tryCount = getValidTryCount();

        RacingGame racingGame = initializeGame(carNames);

        outputView.printResultHeader();
        outputView.printRoundResult(createRacingCarDtos(racingGame));
        for (int roundCount = 0; roundCount < tryCount; roundCount++) {
            racingGame.runRound();
            outputView.printRoundResult(createRacingCarDtos(racingGame));
        }

        List<String> winningCarsName = racingGame.findWinningCarsName();
        outputView.printWinners(winningCarsName);
    }

    private List<String> getValidCarNames() {
        try {
            String carNames = inputView.readCarName();
            List<String> parsedCarNames = Parser.parsing(carNames, ",");
            Validator.validateNames(parsedCarNames);
            return parsedCarNames;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getValidCarNames();
        }
    }

    private int getValidTryCount() {
        try {
            String tryCount = inputView.readTryCount();
            Validator.validateTryCount(tryCount);
            return Integer.parseInt(tryCount);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getValidTryCount();
        }
    }

    private RacingGame initializeGame(List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream().map(RacingCar::new).collect(Collectors.toUnmodifiableList());
        return new RacingGame(racingCars, new RandomNumberGenerator());
    }

    private List<RacingCarDto> createRacingCarDtos(RacingGame racingGame) {
        return racingGame.getStatus()
                .stream()
                .map(RacingCarDto::from)
                .collect(Collectors.toList());
    }
}
