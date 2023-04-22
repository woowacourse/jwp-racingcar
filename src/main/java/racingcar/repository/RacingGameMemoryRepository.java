package racingcar.repository;

import racingcar.repository.dto.RacingGameDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RacingGameMemoryRepository implements RacingGameRepository {
    private static final Map<Integer, RacingGameDto> repo = new ConcurrentHashMap<>();
    private static final Deque<Integer> ids = new ConcurrentLinkedDeque<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    private static final int PLUS_ID_VALUE = 10;

    @Override
    public int save(final String winners, final int count) {
        final int currentId = getCurrentId();
        repo.put(currentId, new RacingGameDto(currentId, winners, LocalDateTime.now(), count));
        return currentId;
    }

    private int getCurrentId() {
        final Integer id = ids.pollFirst();
        ids.addLast(id + PLUS_ID_VALUE);
        return id;
    }

    @Override
    public Optional<RacingGameDto> findById(final int id) {
        return Optional.ofNullable(repo.getOrDefault(id, null));
    }

    @Override
    public List<RacingGameDto> findAll() {
        return new ArrayList<>(repo.values());
    }

    public void reset() {
        repo.clear();
        ids.clear();
        ids.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
