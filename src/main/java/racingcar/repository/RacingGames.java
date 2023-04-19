package racingcar.repository;

import racingcar.domain.RacingGame;

import java.util.List;

public interface RacingGames {

    RacingGame save(RacingGame racingGame);

    List<RacingGame> getAllRacingGames();

}
