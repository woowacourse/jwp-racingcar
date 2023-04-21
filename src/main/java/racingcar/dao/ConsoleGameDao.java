package racingcar.dao;

import racingcar.entity.Game;
import racingcar.entity.GameId;

import java.util.ArrayList;
import java.util.List;

public class ConsoleGameDao implements GameDao {

    private final List<Game> games = new ArrayList<>();
    private int id = 0;

    @Override
    public GameId saveAndGetGameId(final Game game) {
        Game savedGame = new Game(++id, game.getTrial(), game.getCreatedAt());
        games.add(savedGame);
        return new GameId(id);
    }
}
