package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.controller.dto.PlaysRequestDto;
import racingcar.controller.dto.PlaysResponseDto;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameCount;
import racingcar.domain.PowerGenerator;
import racingcar.service.RacingCarService;
import racingcar.util.CarNamesDivider;

import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.*;

@RestController
public class RacingController {

    private final RacingCarService racingCarService;

    @Autowired
    public RacingController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlaysResponseDto>> query() {
        return ResponseEntity.ok()
                .body(racingCarService.query());
    }

    @PostMapping("/plays")
    public ResponseEntity<PlaysResponseDto> submit(@RequestBody PlaysRequestDto playsRequestDto) {
        return ResponseEntity.ok()
                .body(racingCarService.plays(playsRequestDto));
    }

}