package racingcar.dao;

import java.util.ArrayList;
import java.util.List;
import racingcar.entity.Game;

public class GameInMemoryDao implements GameDao {

    private final List<Game> games;

    public GameInMemoryDao() {
        games = new ArrayList<>();
    }

    @Override
    public Long insert(Game game) {
        games.add(game);
        return (long) games.size();
    }

    @Override
    public List<Game> findAll() {
        return new ArrayList<>(games);
    }
}
