package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponseDto;

@Service
public class RacingCarService {

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;

    public RacingCarService(final RacingGameDao racingGameDao, final RacingCarDao racingCarDao) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
    }

    @Transactional
    public RacingGameResponseDto play(final RacingGame racingGame) {
        final int trialCount = racingGame.getCount();
        playGame(racingGame);
        saveRacingGame(racingGame, trialCount);
        return RacingGameResponseDto.of(racingGame.findWinners(), racingGame.getCurrentResult());
    }

    private static void playGame(final RacingGame racingGame) {
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
    }

    private void saveRacingGame(final RacingGame racingGame, final int trialCount) {
        final Long gameId = saveGame(racingGame, trialCount);
        saveCars(racingGame, gameId);
    }

    private Long saveGame(final RacingGame racingGame, final int trialCount) {
        final String names = String.join(",", racingGame.findWinners());
        return racingGameDao.save(names, trialCount);
    }

    private void saveCars(final RacingGame racingGame, final Long gameId) {
        for (final Car car : racingGame.getCurrentResult()) {
            racingCarDao.save(gameId, car);
        }
    }
}
