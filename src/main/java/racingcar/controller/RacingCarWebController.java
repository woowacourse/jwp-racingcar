package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.HistoryResponseDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.ResultResponseDto;
import racingcar.service.RacingCarService;

import java.util.List;

@RestController
public class RacingCarWebController {

    private final RacingCarService racingCarService;

    public RacingCarWebController(RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<ResultResponseDto> play(@RequestBody RacingGameRequestDto request) {
        ResultResponseDto responseDto = racingCarService.play(request);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<HistoryResponseDto> showAllRacingGameHistory() {
        HistoryResponseDto historyResponseDto = racingCarService.getAllRacingGameHistory();
        return ResponseEntity.ok().body(historyResponseDto);
    }
}
