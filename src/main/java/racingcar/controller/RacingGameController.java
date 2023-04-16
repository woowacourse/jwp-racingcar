package racingcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.RacingGame;
import racingcar.domain.Winner;
import racingcar.service.RacingGameService;
import racingcar.utils.RandomNumberGenerator;

@RestController
public class RacingGameController {

    public static final String DELIMITER = ",";

    private final RacingGameService racingGameService;

    @Autowired
    public RacingGameController(final RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping(value = "/plays", consumes = "application/json")
    @ResponseBody
    public ResponseDto play(@RequestBody RequestDto requestDto) {
        String names = requestDto.getNames();
        Integer count = requestDto.getCount();

        RacingGame racingGame = racingGameService.play(names, count, new RandomNumberGenerator());
        racingGameService.save(racingGame);

        return new ResponseDto(getWinners(racingGame), racingGame.getCars());
    }

    private String getWinners(RacingGame racingGame) {
        Winner winner = racingGame.getWinner();
        List<String> winnerNames = winner.getWinnerNames();
        return String.join(DELIMITER, winnerNames);
    }
}
