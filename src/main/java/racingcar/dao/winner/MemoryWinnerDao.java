package racingcar.dao.winner;

import racingcar.dto.WinnerDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryWinnerDao implements WinnerDao {
    private static final Map<Long, WinnerDto> winner = new LinkedHashMap<>();
    private static long id = 1L;
    
    @Override
    public void insert(final WinnerDto winnerDto) {
        winner.put(id++, winnerDto);
    }
    
    @Override
    public List<WinnerDto> findWinnerDtosByGameId(final long gameId) {
        return getSortedWinnerIds().stream()
                .map(winner::get)
                .filter(winnerDto -> winnerDto.getGameId() == gameId)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private List<Long> getSortedWinnerIds() {
        return winner.keySet().stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }
}
