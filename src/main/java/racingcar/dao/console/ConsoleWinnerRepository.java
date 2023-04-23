package racingcar.dao.console;

import racingcar.dao.WinnerRepository;
import racingcar.dao.entity.WinnerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleWinnerRepository implements WinnerRepository {

    private static final Integer START_ID = 1;
    Map<Integer,WinnerEntity> winners = new HashMap<>();
    private int serialNumber = START_ID;

    @Override
    public List<Integer> saveAll(final List<WinnerEntity> winnerEntities) {
        List<Integer> winnersIds = new ArrayList<>();
        for(WinnerEntity winner : winnerEntities) {
            int winnerId = serialNumber;
            winners.put(winnerId, winner);
            winnersIds.add(winnerId);
            serialNumber ++;
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
