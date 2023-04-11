package racing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.CarFactory;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.controller.dto.response.RacingCarStateResponse;
import racing.controller.dto.response.RacingGameResultResponse;
import racing.domain.Cars;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class RacingController {

//    private final Cars cars;

//    public RacingController(Cars cars) {
//        this.cars = cars;
//    }

    @PostMapping("/plays")
    public RacingGameResultResponse start(@RequestBody RacingGameInfoRequest request) {
//        OutputView.printPhrase();
        Cars cars = CarFactory.carFactory(request.getNames());
        move(cars, request.getCount());

        List<String> winners = cars.getWinners();
        String winnersResponse = String.join("", winners);
        List<RacingCarStateResponse> racingCarsState = cars.getCars().stream()
                .map(car -> new RacingCarStateResponse(car.getName(), car.getStep()))
                .collect(Collectors.toList());

        return new RacingGameResultResponse(winnersResponse, racingCarsState);
    }

//    private void move(int count) {
//        while(count-- > 0) {
//            cars.calculator(new Random());
//            OutputView.printProcessing(cars);
//        }
//    }

    private void move(Cars cars, int count) {
        while(count-- > 0) {
            cars.calculator(new Random());
        }
    }

}
