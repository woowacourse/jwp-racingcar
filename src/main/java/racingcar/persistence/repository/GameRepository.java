package racingcar.persistence.repository;

import racingcar.domain.RacingGame;

import java.util.List;

public interface GameRepository {

    void saveGame(final RacingGame racingGame);

    List<RacingGame> selectAllGames();
}
