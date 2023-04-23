package racingcar.dao.console;

import java.util.concurrent.atomic.AtomicInteger;
import racingcar.dao.GameRepository;
import racingcar.dao.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleGameRepository implements GameRepository {

    private List<GameEntity> games = new ArrayList<>();
    private AtomicInteger serialNumber = new AtomicInteger(1);

    @Override
    public int save(final GameEntity gameEntity) {
        int gameId = serialNumber.get();
        GameEntity game = new GameEntity(gameId, gameEntity.getTryCount(), gameEntity.getCreatedAt());
        games.add(game);
        serialNumber.incrementAndGet();
        return gameId;
    }

    @Override
    public List<Integer> findGameIds() {
        return games.stream()
                .map(game -> game.getGameId())
                .collect(Collectors.toList());
    }
}
