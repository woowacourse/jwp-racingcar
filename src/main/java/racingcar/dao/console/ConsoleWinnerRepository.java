package racingcar.dao.console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import racingcar.dao.WinnerRepository;
import racingcar.dao.entity.WinnerEntity;

public class ConsoleWinnerRepository implements WinnerRepository {

    Map<Integer,WinnerEntity> winners = new HashMap<>();
    private AtomicInteger serialNumber = new AtomicInteger(1);

    @Override
    public void saveAll(final List<WinnerEntity> winnerEntities) {
        for(WinnerEntity winner : winnerEntities) {
            winners.put(serialNumber.getAndIncrement(), winner);
        }
    }

    @Override
    public List<Integer> findWinnerCarIdsByGameId(final int gameId) {
        return winners.values().stream()
                .filter(winner -> winner.getGameId() == gameId)
                .map(WinnerEntity::getCarId)
                .collect(Collectors.toList());
    }
}
