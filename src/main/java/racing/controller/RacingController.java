package racing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racing.CarFactory;
import racing.controller.dto.request.RacingGameInfoRequest;
import racing.domain.Cars;

import java.util.Random;

@RestController
public class RacingController {

//    private final Cars cars;

//    public RacingController(Cars cars) {
//        this.cars = cars;
//    }

    @PostMapping("/plays")
    public void start(@RequestBody RacingGameInfoRequest request) {
//        OutputView.printPhrase();
        Cars cars = CarFactory.carFactory(request.getNames());
        move(cars, request.getCount());

        System.out.println("ls");
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
