package racingcar.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingRequest;
import racingcar.dto.RacingResponse;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingService;

@RestController
public final class RacingController {

    private final RacingService racingService;

    @Autowired
    public RacingController(final RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping(path = "/plays")
    public RacingResponse playRacingGame(
            @Valid @RequestBody final RacingRequest racingRequest) {
        RacingResultDto racingResultDto = this.racingService.playRacingGame(racingRequest);
        return RacingResponse.from(racingResultDto);
    }
}
