package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.CarFactory;
import racingcar.domain.car.Cars;
import racingcar.domain.game.GameRecorder;
import racingcar.domain.game.GameResultOfCar;
import racingcar.domain.game.GameSystem;
import racingcar.domain.game.RandomSingleDigitGenerator;
import racingcar.dto.CarDTO;
import racingcar.dto.RacingGameResponseDTO;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleController {

    private static final String DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = makeCars(inputView.readCarNames());

        final int count = inputView.readGameRound();
        GameSystem gameSystem = new GameSystem(count, new GameRecorder(new ArrayList<>()));
        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());
        String winners = getWinners(gameSystem).stream().collect(Collectors.joining(DELIMITER));

        final RacingGameResponseDTO racingGameResponseDTO = new RacingGameResponseDTO(winners,
                getCarDTOs(count, gameSystem));

        outputView.printResult(racingGameResponseDTO);
    }

    private Cars makeCars(final List<String> carNames) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }

    private List<String> getWinners(final GameSystem gameSystem) {
        List<GameResultOfCar> winnersGameResult = gameSystem.getWinnersGameResult();
        return winnersGameResult.stream()
                .map(gameResultOfCar -> gameResultOfCar.getCarName())
                .collect(Collectors.toList());
    }

    private List<CarDTO> getCarDTOs(final int count, final GameSystem gameSystem) {
        List<GameResultOfCar> allGameResult = gameSystem.getAllGameResult();
        return allGameResult.stream()
                .filter(gameResultOfCar -> gameResultOfCar.isSameGameRound(count))
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());
    }
}
