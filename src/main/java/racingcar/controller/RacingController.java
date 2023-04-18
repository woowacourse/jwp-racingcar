package racingcar.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.RacingInfoRequestDto;
import racingcar.dto.RacingResultDto;
import racingcar.dto.RacingResultResponseDto;
import racingcar.service.RacingService;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/plays")
    public List<RacingResultResponseDto> findAllRaceResults() {
        List<RacingResultDto> results = racingService.findAllResults();
        return results.stream()
                .map(RacingResultResponseDto::new)
                .collect(Collectors.toList());
    }
}
