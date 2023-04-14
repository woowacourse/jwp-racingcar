package racingcar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.PlayersHistoryDao;
import racingcar.dao.RacingHistoryDao;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameDto;
import racingcar.dto.RacingGameResultDto;
import racingcar.utils.InputUtil;

@RestController
public class RacingGameController {
    @Autowired
    private RacingHistoryDao racingHistoryDao;
    @Autowired
    private PlayersHistoryDao playersHistoryDao;

    @PostMapping(path = "/plays", consumes = "application/json")
    public ResponseEntity<RacingGameResultDto> play(@RequestBody final RacingGameDto racingGameDto) {

        final List<String> names = InputUtil.splitNames(racingGameDto.getNames());
        final RacingGame racingGame = new RacingGame(names, racingGameDto.getCount());
        racingGame.start();

        final RacingGameResultDto racingGameResultDto = racingGame.convertToDto();
        final int resultId = racingHistoryDao.insertResult(racingGameResultDto);
        playersHistoryDao.insertResult(racingGameResultDto.getRacingCars(), resultId);
        return ResponseEntity.ok(racingGameResultDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
