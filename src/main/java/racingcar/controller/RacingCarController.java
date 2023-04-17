package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.CarFactory;
import racingcar.domain.Cars;
import racingcar.dto.MoveRequestDto;
import racingcar.dto.MoveResponseDto;
import racingcar.genertor.NumberGenerator;
import racingcar.service.RacingCarService;

import java.util.Arrays;
import java.util.List;

@RestController
public class RacingCarController {
    private final RacingCarService racingCarService;
    private final NumberGenerator numberGenerator;

    @Autowired
    public RacingCarController(final RacingCarService racingCarService, final NumberGenerator numberGenerator) {
        this.racingCarService = racingCarService;
        this.numberGenerator = numberGenerator;
    }

    @PostMapping("/plays")
    public ResponseEntity<MoveResponseDto> play(@RequestBody MoveRequestDto moveRequestDto) {
        List<String> carNames = Arrays.asList(moveRequestDto.getNames().split(","));
        Cars cars = new Cars(CarFactory.buildCars(carNames));
        play(cars, moveRequestDto.getCount(), numberGenerator);
        MoveResponseDto moveResponseDto = new MoveResponseDto(cars.findWinners(), cars.getCars());
        racingCarService.saveResult(moveRequestDto.getCount(), cars);
        return ResponseEntity.ok().body(moveResponseDto);
    }

    public void play(Cars cars, int count, NumberGenerator numberGenerator) {
        while (count-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }
}
