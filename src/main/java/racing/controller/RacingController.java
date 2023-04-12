package racing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.CarEntity;
import racing.CarFactory;
import racing.RacingGameDao;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.RacingCarStateResponse;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.domain.Cars;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class RacingController {

    private final RacingGameDao racingGameDao;

    public RacingController(RacingGameDao racingGameDao) {
        this.racingGameDao = racingGameDao;
    }

    @PostMapping("/plays")
    public RacingGameResultResponse start(@RequestBody RacingGameInfoRequest request) {
        Cars cars = CarFactory.carFactory(request.getNames());
        move(cars, request.getCount());

        List<String> winners = cars.getWinners();
        String winnersResponse = String.join("", winners);
        List<RacingCarStateResponse> racingCarsState = cars.getCars().stream()
                .map(car -> new RacingCarStateResponse(car.getName(), car.getStep()))
                .collect(Collectors.toList());

        Long gameId = racingGameDao.saveGame(request.getCount());
        cars.getCars().stream()
                .map(car -> CarEntity.of(gameId, car, winners.contains(car.getName())))
                .forEach(racingGameDao::saveCar);

        return new RacingGameResultResponse(winnersResponse, racingCarsState);
    }

    private void move(Cars cars, int count) {
        while(count-- > 0) {
            cars.calculator(new Random());
        }
    }

}
