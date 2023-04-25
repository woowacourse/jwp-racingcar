package racingcar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.service.dto.GameHistoryDto;
import racingcar.controller.dto.GameRequest;
import racingcar.controller.dto.GameResponse;
import racingcar.service.dto.RacingGameDto;
import racingcar.service.RacingGameService;
import racingcar.utils.Parser;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class RacingGameWebController {

    private static final ApplicationType applicationType = ApplicationType.WEB;

    private final RacingGameService racingGameService;
    private final Parser parser;

    public RacingGameWebController(final RacingGameService racingGameService, Parser parser) {
        this.racingGameService = racingGameService;
        this.parser = parser;
    }

    @PostMapping("/plays")
    public GameResponse doGame(@RequestBody final GameRequest gameRequest) {
        RacingGameDto racingGameDto = new RacingGameDto(parser.sliceByComma(gameRequest.getNames()), gameRequest.getCount(), applicationType);
        GameHistoryDto gameHistoryDto = racingGameService.playGame(racingGameDto);
        return GameResponse.from(gameHistoryDto);
    }

    @GetMapping("/plays")
    public List<GameResponse> findGame() {
        return racingGameService.findRacingGameHistory().stream()
                .map(GameResponse::from)
                .collect(toList());
    }
}
