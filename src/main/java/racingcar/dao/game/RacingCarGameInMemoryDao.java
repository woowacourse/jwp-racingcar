package racingcar.dao.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.dao.entity.Game;
import racingcar.dto.RacingCarGameResultResponseDto;

public class RacingCarGameInMemoryDao implements RacingCarGameDao{

    private static final Map<Long, Game> gameMemory = new HashMap<>();

    @Override
    public Long insertGameWithKeyHolder(final Game game) {
        long lastKey = gameMemory.keySet().size();
        gameMemory.put(lastKey + 1, game);
        return lastKey;
    }

    @Override
    public List<RacingCarGameResultResponseDto> findAll() {
        return null;
    }
}
