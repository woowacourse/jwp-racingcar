package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingResultResponseDto;
import racingcar.dto.RacingInfoRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingService;

@RestController
public class RacingController {
    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public RacingResultResponseDto playRacing(@RequestBody RacingInfoRequestDto dto) {
        RacingResultDto racingResultDto = racingService.race(dto.getNames(), dto.getCount());
        racingService.saveResult(racingResultDto);
        return new RacingResultResponseDto(racingResultDto);
    }
}
