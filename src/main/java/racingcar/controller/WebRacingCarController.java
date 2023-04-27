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
import racingcar.domain.dto.RacingCarResultDto;
import racingcar.service.RacingCarService;

@RestController
@RequestMapping("/plays")
public class WebRacingCarController {

    private final RacingCarService racingCarService;

    public WebRacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @GetMapping
    public ResponseEntity<List<RacingCarGameResponse>> findAllRecords() {
        final List<RacingCarResultDto> racingCarResultDtos = racingCarService.findAllResults();
        final List<RacingCarGameResponse> records = racingCarResultDtos.stream()
            .map(RacingCarGameResponse::from)
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(records);
    }

    @PostMapping
    public ResponseEntity<RacingCarGameResponse> playRacingCar(@RequestBody RacingCarGameRequest racingCarGameRequest) {
        final List<String> names = List.of(racingCarGameRequest.getNames().split(","));
        final int attempt = racingCarGameRequest.getCount();
        final RacingCarResultDto racingCarResultDto = racingCarService.raceCar(names, attempt);
        final RacingCarGameResponse racingCarGameResponse = RacingCarGameResponse.from(racingCarResultDto);
        return ResponseEntity.ok().body(racingCarGameResponse);
    }
}
