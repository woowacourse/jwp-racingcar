package racingcar.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.FinalResultDto;
import racingcar.dto.RequestBodyDTO;
import racingcar.dto.ResultDto;
import racingcar.service.RacingService;

@RestController
public class RacingController {
    private final RacingService racingService;

    public RacingController(RacingService racingService) {
        this.racingService = racingService;
    }

    @PostMapping("/plays")
    public FinalResultDto playRacing(@RequestBody RequestBodyDTO dto) {
        ResultDto resultDto = racingService.race(dto.getNames(), dto.getCount());
        racingService.saveResult(resultDto);
        return new FinalResultDto(resultDto);
    }
}
