package racingcar.controller;

import racingcar.model.car.CarRegisterer;
import racingcar.model.car.Cars;
import racingcar.model.car.Car;
import racingcar.model.manager.CarMoveManager;
import racingcar.util.ValueEditor;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<GameStatus, Supplier<GameStatus>> gameGuide;
    private final CarMoveManager carMoveManager;
    private final CarRegisterer carRegisterer;

    public MainController(InputView inputView, OutputView outputView, CarMoveManager carMoveManager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameGuide = initializeGameGuide();
        this.carMoveManager = carMoveManager;
        this.carRegisterer = new CarRegisterer();
    }

    private Map<GameStatus, Supplier<GameStatus>> initializeGameGuide() {
        Map<GameStatus, Supplier<GameStatus>> gameGuide = new EnumMap<>(GameStatus.class);
        gameGuide.put(GameStatus.CAR_REGISTRATION, this::registerCars);
        gameGuide.put(GameStatus.GAME_START, this::race);
        return gameGuide;
    }

    public void play() {
        GameStatus gameStatus = progress(GameStatus.CAR_REGISTRATION);
        while (gameStatus.isContinue(gameStatus)) {
            gameStatus = progress(gameStatus);
        }
    }

    private GameStatus progress(GameStatus gameStatus) {
        try {
            return gameGuide.get(gameStatus).get();
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception);
            return gameStatus;
        } catch (NullPointerException exception) {
            return GameStatus.GAME_EXIT;
        } catch (Exception exception) {
            return GameStatus.GAME_EXIT;
        }
    }

    private GameStatus registerCars() {
        List<String> carNames = inputView.readCarNames();
        carNames.stream().forEach(carName -> carRegisterer.registerCar(carName));
        return GameStatus.GAME_START;
    }

    private GameStatus race() {
        String moveCountInput = inputView.readMoveCount();
        int moveCount = ValueEditor.parseStringToInt(moveCountInput);
        Cars cars = carRegisterer.prepareCars();
        moveAllCars(moveCount, cars);
        outputView.printResultMessage();
        outputView.printWinners(cars.getWinners());
        return GameStatus.GAME_EXIT;
    }

    private void moveAllCars(int moveCount, Cars cars) {
        for (int i = 0; i < moveCount; i++) {
            cars.moveAllCarsOnce(carMoveManager);
            List<Car> currentResult = cars.getCurrentResult();
            outputView.printResult(currentResult);
        }
    }

    private enum GameStatus {
        CAR_REGISTRATION, GAME_START, GAME_EXIT;

        GameStatus() {
        }

        boolean isContinue(GameStatus gameStatus) {
            return !gameStatus.equals(GAME_EXIT);
        }
    }
}
