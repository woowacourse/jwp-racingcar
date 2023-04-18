package racingcar.dao;

import racingcar.entity.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleGameDao implements GameDao {

    List<Game> games = new ArrayList<>();
    private int id = 0;

    @Override
    public Optional<Integer> saveAndGetId(final Game game) {
        Game savedGame = new Game(++id, game.getTrial(), game.getCreatedAt());
        games.add(savedGame);
        return Optional.of(id);
    }
}
