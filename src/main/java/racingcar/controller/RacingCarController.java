package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.dto.RequestDto;
import racingcar.dto.ResponseDto;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingCarController {
    private final RacingCarService racingCarService;

    @Autowired
    public RacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResponseDto> play(@RequestBody RequestDto requestDto) {
        List<String> carNames = Arrays.asList(requestDto.getNames().split(","));
        Cars cars = new Cars(CarFactory.buildCars(carNames));
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        play(cars, requestDto.getCount(),   numberGenerator);
        ResponseDto responseDto = new ResponseDto(cars.findWinners(), cars.getCars());
        racingCarService.saveResult(requestDto.getCount(), cars);
        return ResponseEntity.ok().body(responseDto);
    }

    public void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }
}
