package racingcar.dao.game;

import racingcar.dto.GameDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryGameDao implements GameDao {
    private static final Map<Long, GameDto> game = new LinkedHashMap<>();
    private static long id = 1L;
    
    @Override
    public long insert(final GameDto gameDto) {
        game.put(id, gameDto);
        return id++;
    }
    
    @Override
    public List<Long> findAllId() {
        return game.keySet().stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }
    
    @Override
    public GameDto findById(final long gameId) {
        return game.get(gameId);
    }
    
    public void deleteAll() {
        id = 1L;
        game.clear();
    }
}
