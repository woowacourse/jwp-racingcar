package racingcar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.result.GameResultOfCar;
import racingcar.domain.system.GameSystem;
import racingcar.dto.CarDTO;
import racingcar.dto.ResponseDTO;

public class RacingCarService {

    public ResponseDTO play(final String names, final int count) {
        final List<String> carNames = Arrays.stream(names.split(",")).collect(Collectors.toList());
        final Cars cars = makeCars(carNames);
        final GameSystem gameSystem = createGameSystem(count);
        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());

        String winners = getWinners(gameSystem);
        List<CarDTO> carDTOs = getCarDTOs(count, gameSystem);

        return new ResponseDTO(winners, carDTOs);
    }

    private Cars makeCars(final List<String> carNames) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }

    private GameSystem createGameSystem(final int gameRound) {
        return new GameSystem(gameRound, new GameRecorder(new ArrayList<>()));
    }

    private String getWinners(final GameSystem gameSystem) {
        List<GameResultOfCar> winnersGameResult = gameSystem.getWinnersGameResult();
        return winnersGameResult.stream()
                .map(gameResultOfCar -> gameResultOfCar.getCarName())
                .collect(Collectors.joining());
    }

    private List<CarDTO> getCarDTOs(final int count, final GameSystem gameSystem) {
        List<GameResultOfCar> allGameResult = gameSystem.getAllGameResult();
        return allGameResult.stream()
                .filter(gameResultOfCar -> gameResultOfCar.isSameGameRound(count))
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());
    }
}