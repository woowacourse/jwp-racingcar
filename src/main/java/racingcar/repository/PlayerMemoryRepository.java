package racingcar.repository;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.repository.dto.PlayerDto;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class PlayerMemoryRepository implements PlayerRepository {
    private static final Map<Integer, PlayerDto> repo = new ConcurrentHashMap<>();
    private static final Deque<Integer> ids = new ConcurrentLinkedDeque<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    private static final int PLUS_ID_VALUE = 10;

    @Override
    public int[] saveAll(final Cars cars, final int racingGameId) {
        final List<Car> racingCars = cars.getRacingCars();
        final int carsSize = racingCars.size();
        final int[] updatedCount = new int[carsSize];
        for (int index = 0; index < carsSize; index++) {
            final Car car = racingCars.get(index);
            final int currentId = getCurrentId();
            repo.put(currentId, new PlayerDto(currentId, car.getNameValue(), car.getPositionValue(), racingGameId));
            updatedCount[index] = currentId;
        }

        return updatedCount;
    }

    private int getCurrentId() {
        final Integer id = ids.pollFirst();
        ids.addLast(id + PLUS_ID_VALUE);
        return id;
    }

    @Override
    public List<PlayerDto> findByRacingGameId(final int racingGameId) {
        return repo.values()
                .stream()
                .filter(playerDto -> playerDto.getRacingGameId() == racingGameId)
                .collect(Collectors.toList());
    }

    public void reset() {
        repo.clear();
        ids.clear();
        ids.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
