package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.database.Database;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponseDto;

import java.util.List;

@Service
public class RacingCarService {

    private final Database database;

    public RacingCarService(final Database database) {
        this.database = database;
    }

    @Transactional
    public RacingGameResponseDto play(final RacingGame racingGame) {
        final int trialCount = racingGame.getCount();
        playGame(racingGame);
        database.save(racingGame, trialCount);
        return RacingGameResponseDto.of(racingGame.findWinners(), racingGame.getCurrentResult());
    }

    private static void playGame(final RacingGame racingGame) {
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
    }

    public List<RacingGameResponseDto> findAllHistories() {
        return database.findAllHistories();
    }
}
