package racingcar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.dao.PlayersHistoryDao;
import racingcar.dao.RacingHistoryDao;
import racingcar.domain.RacingGame;
import racingcar.dto.RacingGameDto;
import racingcar.dto.RacingGameResultDto;
import racingcar.utils.InputUtil;
import racingcar.view.OutputView;

import java.util.List;

@RestController
public class RacingGameController {
    @Autowired
    private RacingHistoryDao racingHistoryDao;
    @Autowired
    private PlayersHistoryDao playersHistoryDao;

    @PostMapping(path = "/plays", consumes = "application/json")
    public ResponseEntity<RacingGameResultDto> play(@RequestBody final RacingGameDto racingGameDto) {

        final RacingGame racingGame = new RacingGame(InputUtil.splitNames(racingGameDto.getNames()),
                racingGameDto.getCount());
        racingGame.start();

        final RacingGameResultDto racingGameResultDto = racingGame.convertToDto();
        final int resultId = racingHistoryDao.insertResult(racingGameResultDto);
        playersHistoryDao.insertResult(racingGameResultDto.getRacingCars(), resultId);
        OutputView.printResult(racingGameResultDto);
        return ResponseEntity.ok(racingGameResultDto);
    }

    @GetMapping(path = "/plays")
    public ResponseEntity<List<RacingGameResultDto>> inquireResult() {
        List<RacingGameResultDto> racingGameResultDtos = racingHistoryDao.getResult();
        return ResponseEntity.ok(racingGameResultDtos);
    }
}
