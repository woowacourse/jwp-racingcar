package racingcar.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dto.GameDto;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.service.RacingCarService;

@RestController
public class RacingCarWebController {

    private static final String DELIMITER = ",";

    private final RacingCarService racingCarService;

    public RacingCarWebController(final RacingCarService racingCarService) {
        this.racingCarService = racingCarService;
    }

    @PostMapping("/plays")
    public GameResponse play(@Valid @RequestBody final GameRequest gameRequest) {
        String names = gameRequest.getNames();
        validateDelimiter(names);
        List<String> splitNames = List.of(names.split(DELIMITER));
        GameDto gameDto = GameDto.of(splitNames, gameRequest.getCount());

        return racingCarService.play(gameDto);
    }

    @GetMapping("/plays")
    public List<GameResponse> getGameResults() {
        return racingCarService.getGameResults();
    }

    private void validateDelimiter(final String names) {
        if (!names.contains(DELIMITER)) {
            throw new IllegalArgumentException("구분자는 쉼표(,)입니다.");
        }
    }
}
