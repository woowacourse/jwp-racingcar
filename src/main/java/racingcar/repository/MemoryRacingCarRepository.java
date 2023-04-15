package racingcar.repository;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

public class MemoryRacingCarRepository implements RacingCarRepository {
    private int gameId = 1;
    private final Map<Integer, List<Car>> playerTable = new HashMap<>();
    private final Map<Integer, List<String>> winnerTable = new HashMap<>();

    @Override
    public void saveWinner(int gameId, List<String> winners) {
        winnerTable.put(gameId, winners);
    }

    @Override
    public void saveCars(int gameId, List<Car> cars) {
        playerTable.put(gameId, cars);
    }

    @Override
    public int saveGame(int count) {
        return gameId++;
    }

    @Override
    public String findWinners(int gameId) {
        return String.join(",", winnerTable.get(gameId));
    }

    @Override
    public List<RacingCarDto> findRacingCars(int gameId) {
        return playerTable.get(gameId).stream()
                .map(car -> new RacingCarDto(car.getName(), car.getPosition()))
                .collect(toList());
    }
}
