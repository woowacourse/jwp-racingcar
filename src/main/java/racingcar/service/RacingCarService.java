package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.RacingCarDao;
import racingcar.dao.RacingGameDao;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingCarService {

    private final RacingGameDao racingGameDao;
    private final RacingCarDao racingCarDao;
    private final NumberGenerator numberGenerator;

    public RacingCarService(
            final RacingGameDao racingGameDao,
            final RacingCarDao racingCarDao,
            final NumberGenerator numberGenerator
    ) {
        this.racingGameDao = racingGameDao;
        this.racingCarDao = racingCarDao;
        this.numberGenerator = numberGenerator;
    }

    @Transactional
    public RacingGameResponse play(final String rawNames, final int count) {
        final List<String> names = Arrays.stream(rawNames.split(","))
                .collect(Collectors.toList());
        final RacingGame racingGame = new RacingGame(numberGenerator, new Cars(names), new Count(count));
        final int trialCount = racingGame.getCount();
        playGame(racingGame);
        saveRacingGame(racingGame, trialCount);
        return RacingGameResponse.of(racingGame.findWinners(), racingGame.getCurrentResult());
    }

    private void playGame(final RacingGame racingGame) {
        while (racingGame.isPlayable()) {
            racingGame.play();
        }
    }

    private void saveRacingGame(final RacingGame racingGame, final int trialCount) {
        final Long gameId = saveGame(racingGame, trialCount);
        racingCarDao.saveAll(gameId, racingGame.getCurrentResult());
    }

    private Long saveGame(final RacingGame racingGame, final int trialCount) {
        final String names = String.join(",", racingGame.findWinners());
        return racingGameDao.save(names, trialCount);
    }
}
