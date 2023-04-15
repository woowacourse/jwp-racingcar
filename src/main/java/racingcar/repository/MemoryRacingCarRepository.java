package racingcar.repository;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingResultResponse;

public class MemoryRacingCarRepository implements RacingCarRepository {
    private static final String WINNER_DELIMITER = ",";
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
        return String.join(WINNER_DELIMITER, winnerTable.get(gameId));
    }

    @Override
    public List<RacingCarDto> findRacingCars(int gameId) {
        return playerTable.get(gameId).stream()
                .map(car -> new RacingCarDto(car.getName(), car.getPosition()))
                .collect(toList());
    }

    @Override
    public List<RacingResultResponse> findAllGameResults() {
        return IntStream.rangeClosed(1, gameId)
                .mapToObj(id -> new RacingResultResponse(findWinners(id), findRacingCars(id)))
                .collect(toList());
    }
}
