package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import racingcar.controller.dto.RacingCarGameRequest;
import racingcar.controller.dto.RacingCarGameResponse;
import racingcar.service.MainRacingCarService;
import racingcar.service.dto.RacingCarResult;

@RestController
@RequestMapping("/plays")
public class WebRacingCarController {

    private final MainRacingCarService mainRacingCarService;

    public WebRacingCarController(final MainRacingCarService mainRacingCarService) {
        this.mainRacingCarService = mainRacingCarService;
    }

    @GetMapping
    public ResponseEntity<List<RacingCarGameResponse>> findAllRecords() {
        final List<RacingCarResult> racingCarResults = mainRacingCarService.findAllResults();
        final List<RacingCarGameResponse> records = racingCarResults.stream()
            .map(RacingCarGameResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(records);
    }

    @PostMapping
    public ResponseEntity<RacingCarGameResponse> playRacingCar(@RequestBody RacingCarGameRequest racingCarGameRequest) {
        final List<String> names = List.of(racingCarGameRequest.getNames().split(","));
        final int attempt = racingCarGameRequest.getCount();
        final RacingCarResult racingCarResult = mainRacingCarService.raceCar(names, attempt);
        final RacingCarGameResponse racingCarGameResponse = RacingCarGameResponse.from(racingCarResult);
        return ResponseEntity.ok().body(racingCarGameResponse);
    }
}
