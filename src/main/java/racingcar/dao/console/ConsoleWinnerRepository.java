package racingcar.dao.console;

import java.util.concurrent.atomic.AtomicInteger;
import racingcar.dao.WinnerRepository;
import racingcar.dao.entity.WinnerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleWinnerRepository implements WinnerRepository {

    Map<Integer,WinnerEntity> winners = new HashMap<>();
    private AtomicInteger serialNumber = new AtomicInteger(1);

    @Override
    public List<Integer> saveAll(final List<WinnerEntity> winnerEntities) {
        List<Integer> winnersIds = new ArrayList<>();
        for(WinnerEntity winner : winnerEntities) {
            int winnerId = serialNumber.get();
            winners.put(winnerId, winner);
            winnersIds.add(winnerId);
            serialNumber.incrementAndGet();
        }
        return winnersIds;
    }

    @Override
    public List<Integer> findWinnerCarIdsByGameId(final int gameId) {
        return winners.values().stream()
                .filter(winner -> winner.getGameId() == gameId)
                .map(WinnerEntity::getCarId)
                .collect(Collectors.toList());
    }
}
