package racingcar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.model.Cars;
import racingcar.model.Trial;
import racingcar.service.RacingCarService;
import racingcar.ui.WebInputConvertor;

@RestController
public class WebController {
    private final RacingCarService racingcarService;

    public WebController(RacingCarService racingcarService) {
        this.racingcarService = racingcarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingCarResponseDto> plays(@Valid @RequestBody RacingCarRequestDto request) {
        List<String> carNames = WebInputConvertor.carNames(request.getNames());
        Cars cars = Cars.from(carNames);
        int tryCount = WebInputConvertor.tryCount(request.getCount());
        Trial trial = new Trial(tryCount);
        return ResponseEntity.ok()
            .body(racingcarService.play(cars, trial));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<RacingCarResponseDto>> allResults() {
        return ResponseEntity.ok()
            .body(racingcarService.allGames());
    }
}
