package racingcar.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.result.GameResultOfCar;
import racingcar.domain.system.GameSystem;
import racingcar.dto.CarDTO;
import racingcar.dto.RequestDTO;
import racingcar.dto.ResponseDTO;

@RestController
public class WebController {

    @PostMapping("/plays")
    public ResponseEntity createGame(@RequestBody RequestDTO requestDTO) {
        final String names = requestDTO.getNames();
        final List<String> carNames = Arrays.stream(names.split(",")).collect(Collectors.toList());
        final Cars cars = makeCars(carNames);

        final int count = requestDTO.getCount();
        final GameSystem gameSystem = createGameSystem(count);

        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());


        //winners
        List<GameResultOfCar> winnersGameResult = gameSystem.getWinnersGameResult();

        String winners = winnersGameResult.stream()
                .map(gameResultOfCar -> gameResultOfCar.getCarName())
                .collect(Collectors.joining());

        //result
        List<GameResultOfCar> allGameResult = gameSystem.getAllGameResult();
        List<CarDTO> carDTOs = allGameResult.stream()
                .filter(gameResultOfCar -> gameResultOfCar.isSameGameRound(count))
                .map(gameResultOfCar -> new CarDTO(gameResultOfCar.getCarName(), gameResultOfCar.getPosition()))
                .collect(Collectors.toList());

        ResponseDTO responseDTO = new ResponseDTO(winners, carDTOs);
        return ResponseEntity.ok().body(responseDTO);
    }

    private Cars makeCars(final List<String> carNames) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }

    private GameSystem createGameSystem(final int gameRound) {
        return new GameSystem(gameRound, new GameRecorder(new ArrayList<>()));
    }
}