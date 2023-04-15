package racingcar.dao;

import racingcar.domain.car.Car;
import racingcar.domain.game.RacingCarGame;

import java.util.List;

public interface RacingCarGameDao {


    int save(RacingCarGame racingCarGame, int count);

    List<Car> findCarsByGameId(int gameId);

    List<String> findWinners(int gameId);
}
