package racingcar.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import racingcar.domain.RacingGame;

@Repository
public class InMemoryRacingRepository implements RacingRepository {

    private final List<RacingGame> racingGames = new ArrayList<>();

    public void saveGameResult(final RacingGame racingGame, final int trialCount) {
        racingGames.add(racingGame);
    }

    public List<RacingGame> findAllRacingGames() {
        return racingGames;
    }
}
