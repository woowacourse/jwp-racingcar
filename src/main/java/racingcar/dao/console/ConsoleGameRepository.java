package racingcar.dao.console;

import racingcar.dao.GameRepository;
import racingcar.dao.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleGameRepository implements GameRepository {

    private static final Integer START_ID = 1;
    private List<GameEntity> games = new ArrayList<>();
    private int serialNumber = START_ID;

    @Override
    public int save(final GameEntity gameEntity) {
        int gameId = serialNumber;
        GameEntity game = new GameEntity(gameId, gameEntity.getTryCount(), gameEntity.getCreatedAt());
        games.add(game);
        serialNumber ++;
        return gameId;
    }

    @Override
    public List<Integer> findGameIds() {
        return games.stream()
                .map(game -> game.getGameId())
                .collect(Collectors.toList());
    }
}
