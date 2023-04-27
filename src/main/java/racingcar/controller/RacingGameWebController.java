package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.ResultDto;
import racingcar.dto.UserInputDto;
import racingcar.service.RacingGameService;

import java.util.List;

@RestController
public class RacingGameWebController {

    private final RacingGameService racingGameService;

    public RacingGameWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultDto> racingGame(@RequestBody UserInputDto inputDto) {
        final Long gameResultId = racingGameService.addGameResultAndCars(inputDto);
        final ResultDto resultDto = racingGameService.findResults(gameResultId);
        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultDto>> historyInquiry() {
        final List<ResultDto> histories = racingGameService.findHistories();
        return ResponseEntity.ok(histories);
    }
}
