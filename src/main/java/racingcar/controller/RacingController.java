package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingInfoRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.service.RacingService;

import java.util.List;

@RestController
public class RacingController {
    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public RacingResultDto playRacing(@RequestBody RacingInfoRequestDto dto) {
        return racingService.race(dto.getNames(), dto.getCount());
    }

    @GetMapping("/plays")
    public List<RacingResultDto> findAllRaceResults() {
        return racingService.findAllResults();
    }
}
