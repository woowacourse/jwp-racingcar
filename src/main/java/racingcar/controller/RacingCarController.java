package racingcar.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.PlayRequestDto;
import racingcar.dto.PlayResponseDto;
import racingcar.service.RacingCarService;
import racingcar.view.util.TextParser;

@RestController
public class RacingCarController {

    private static final String CAR_NAMES_DELIMITER = ",";
    private final RacingCarService racingCarService;

    public RacingCarController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public ResponseEntity<PlayResponseDto> play(@RequestBody PlayRequestDto playRequestDto) {
        final int count = playRequestDto.getCount();
        final List<String> carNames = TextParser.parseByDelimiter(playRequestDto.getNames(), CAR_NAMES_DELIMITER);

        final PlayResponseDto playResult = racingCarService.playGame(count, carNames);

        return ResponseEntity.ok(playResult);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<PlayResponseDto>> history() {
        List<PlayResponseDto> history = racingCarService.findAllGames();
        return ResponseEntity.ok(history);
    }
}
