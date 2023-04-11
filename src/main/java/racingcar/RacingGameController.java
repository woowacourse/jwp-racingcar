package racingcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.PlayersHistoryDao;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameDto;
import racingcar.domain.RacingGameResultDto;
import racingcar.domain.RacingHistoryDao;
import racingcar.utils.InputUtil;
import racingcar.utils.powerGenerator.RandomPowerGenerator;

@RestController
public class RacingGameController {
    @Autowired
    private RacingHistoryDao racingHistoryDao;

    @Autowired
    private PlayersHistoryDao playersHistoryDao;

    @PostMapping(path = "/plays", consumes = "application/json")
    public ResponseEntity<RacingGameResultDto> play(@RequestBody final RacingGameDto racingGameDto) {
        final RandomPowerGenerator randomPowerGenerator = new RandomPowerGenerator();

        final RacingGame racingGame = new RacingGame(InputUtil.splitNames(racingGameDto.getNames()),
                racingGameDto.getCount(),
                randomPowerGenerator);
        racingGame.start();

        final RacingGameResultDto racingGameResultDto = racingGame.convert();
        final int resultId = racingHistoryDao.insertResult(racingGameResultDto);
        playersHistoryDao.insertResult(racingGameResultDto.getRacingCars(), resultId);
        return ResponseEntity.ok(racingGameResultDto);
    }
}
