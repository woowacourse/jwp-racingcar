package racingcar.repository.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.repository.entity.GameWinnerEntity;

public class InMemoryGameWinnerDao implements GameWinnerDao {

    private final List<GameWinnerEntity> gameWinnerEntities = new ArrayList<>();

    @Override
    public long save(final GameWinnerEntity gameWinnerEntity) {
        final Long gameId = gameWinnerEntity.getGameId();
        final Long userId = gameWinnerEntity.getUserId();

        if (gameWinnerEntities.isEmpty()) {
            final Long id = 1L;
            gameWinnerEntities.add(new GameWinnerEntity(id, gameId, userId));
            return id;
        }

        final int lastIndex = gameWinnerEntities.size() - 1;
        final Long id = gameWinnerEntities.get(lastIndex).getId() + 1;
        gameWinnerEntities.add(new GameWinnerEntity(id, gameId, userId));
        return id;
    }

    @Override
    public List<GameWinnerEntity> findByGameId(final long gameId) {
        return gameWinnerEntities.stream()
                .filter(gameWinnerEntity -> gameWinnerEntity.getGameId() == gameId)
                .collect(Collectors.toList());
    }
}
