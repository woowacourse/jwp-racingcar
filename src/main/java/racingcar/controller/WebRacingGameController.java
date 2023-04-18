package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Names;
import racingcar.domain.TryCount;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.service.RacingGameService;

import java.util.List;

@RestController
public class WebRacingGameController {
    private final RacingGameService racingGameService;

    @Autowired
    public WebRacingGameController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    public ResponseEntity<GameResponseDto> playGame(@RequestBody GameRequestDto request) {
        Names names = convertToNames(request.getNames());
        TryCount tryCount = TryCount.from(request.getCount());

        GameResponseDto response = racingGameService.play(names, tryCount);

        return ResponseEntity.ok(response);
    }

    private Names convertToNames(String names) {
        return Names.from(List.of(names.split(",")));
    }

    @GetMapping("/plays")
    public ResponseEntity<List<GameResponseDto>> findAllResults() {
        List<GameResponseDto> allGameAndPlayerResults = racingGameService.findAllGameAndPlayerResults();

        return ResponseEntity.ok(allGameAndPlayerResults);
    }
}
