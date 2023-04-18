package racingcar.repositoryImpl;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.RacingGame;
import racingcar.repository.RacingGameRepository;

public class DefaultRacingGameRepository implements RacingGameRepository {

    private final List<RacingGame> racingGames = new ArrayList<>();

    @Override
    public RacingGame insert(final RacingGame racingGame) {
        racingGames.add(racingGame);
        return new RacingGame(racingGames.size(), racingGame.getCars(),
                racingGame.getCount().getTargetCount());
    }

    @Override
    public List<RacingGame> findAll() {
        return racingGames;
    }
}
