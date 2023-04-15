package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarRequest;
import racingcar.dto.RacingResultResponse;
import racingcar.service.RacingCarService;

@RestController
public class WebRacingCarController {
    private final RacingCarService racingCarService;

    public WebRacingCarController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<RacingResultResponse> play(@RequestBody RacingCarRequest request) {
        List<String> carNames = getCarNames(request.getNames());
        int gameId = racingCarService.playRacingGame(carNames, request.getCount());

        String winners = racingCarService.findWinners(gameId);
        List<RacingCarDto> racingCars = racingCarService.findRacingCars(gameId);
        return ResponseEntity.ok()
                .body(new RacingResultResponse(winners, racingCars));
    }

    private List<String> getCarNames(String names) {
        return List.of(names.split(","));
    }
}
