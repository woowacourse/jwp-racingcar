package racingcar.dao.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.dao.entity.Game;

public class MemoryGameDao implements GameDao {
    private long serial = 0L;
    private final List<Game> gameTable = new ArrayList<>();

    @Override
    public long saveGame(Game game) {
        serial += 1;
        Game newGame = new Game(serial, game.getTrialCount());
        gameTable.add(newGame);
        return serial;
    }

    @Override
    public List<Long> getGameIds() {
        return gameTable.stream()
                .map(Game::getId)
                .collect(Collectors.toUnmodifiableList());
    }
}
