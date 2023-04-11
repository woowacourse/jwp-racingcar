package racingcar;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import racingcar.controller.GamePlay;
import racingcar.domain.Cars;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.service.CarFactory;
import racingcar.view.OutputView;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingCarController {

    @PostMapping("/plays")
    public ResponseEntity<ResponseDto> play(@RequestBody RequestDto requestDto) {
        List<String> carNames = Arrays.asList(requestDto.getNames().split(","));
        Cars cars = new Cars(CarFactory.buildCars(carNames));
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        play(cars, requestDto.getCount(),   numberGenerator);
        ResponseDto responseDto = new ResponseDto(cars.findWinners(), cars.getCars());
        return ResponseEntity.ok().body(responseDto);
    }

    public void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }
}
