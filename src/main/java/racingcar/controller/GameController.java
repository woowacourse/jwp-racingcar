package racingcar.controller;

import java.util.Arrays;
import java.util.stream.Collectors;
import racingcar.dao.entity.GameEntity;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.result.GameResultOfCar;
import racingcar.domain.system.GameSystem;
import racingcar.dto.CarDTO;
import racingcar.dto.RacingGameResponseDTO;
import racingcar.dto.ResultDTO;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static final String DELIMITER = ",";

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = makeCars(inputView.readCarNames());

        final int count = inputView.readGameRound();
        GameSystem gameSystem = new GameSystem(count, new GameRecorder(new ArrayList<>()));
        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());
        String winners = getWinners(gameSystem).stream().collect(Collectors.joining(DELIMITER));

        final RacingGameResponseDTO racingGameResponseDTO = new RacingGameResponseDTO(winners, getCarDTOs(count, gameSystem));

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
