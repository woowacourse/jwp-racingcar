package racingcar.repository;

import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameMapper {

    public static Game mapToGame(RacingGame racingGame) {
        return new Game(racingGame.getGameId(), racingGame.getCount(), racingGame.getWinners());
    }

    public static List<Player> mapToPlayers(RacingGame racingGame) {
        return racingGame.getCars().stream()
                .map(car -> mapToPlayer(racingGame.getGameId(), car))
                .collect(Collectors.toList());
    }

    private static Player mapToPlayer(Long gameId, Car car) {
        return new Player(car.getName(), car.getPosition(), gameId);
    }
}
