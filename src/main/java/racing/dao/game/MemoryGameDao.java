package racing.dao.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racing.domain.Game;
import racing.dto.GameDto;

public class MemoryGameDao implements GameDao {

    private static int gameId = 1;
    private static final Map<Integer, GameDto> games = new LinkedHashMap<>();

    @Override
    public int insert(final Game game) {
        final int currentId = gameId;
        final GameDto gameDto = new GameDto(game.getTotalCount());
        games.put(gameId++, gameDto);
        return currentId;
    }

    @Override
    public List<Integer> getAllGameId() {
        return List.copyOf(games.keySet());
    }
}
