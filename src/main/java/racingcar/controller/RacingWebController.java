package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.dto.HistoryResponseDto;
import racingcar.service.RacingGameService;

@RestController
public class RacingWebController {
    private final RacingGameService racingGameService;

    public RacingWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(path = "/plays")
    public GameResponseDto play(@Valid @RequestBody GameRequestDto gameRequestDto) {
        return racingGameService.play(gameRequestDto.getNames(), gameRequestDto.getCount());
    }

    @GetMapping(path = "/plays")
    public List<HistoryResponseDto> findHistory() {
        return racingGameService.findHistory();
    }
}
