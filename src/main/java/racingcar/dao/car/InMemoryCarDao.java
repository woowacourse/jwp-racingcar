package racingcar.dao.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.dao.dto.GameFinishedCarDto;
import racingcar.model.Car;

public class InMemoryCarDao implements CarDao {

    private static final Map<Integer, List<CarEntity>> CAR_CACHE = new HashMap<>();

    @Override
    public void saveAll(final int gameId, final List<Car> cars, final List<String> winners) {
        List<CarEntity> carEntities = new ArrayList<>();
        for (Car car : cars) {
            final String carName = car.getName();
            final boolean isWinner = winners.contains(carName);
            carEntities.add(new CarEntity(gameId, carName, car.getPosition(), isWinner));
        }
        CAR_CACHE.put(gameId, carEntities);
    }

    @Override
    public Map<Integer, List<GameFinishedCarDto>> selectAll() {
        Map<Integer, List<GameFinishedCarDto>> carsByGame = new HashMap<>();
        for (Integer gameId : CAR_CACHE.keySet()) {
            final List<GameFinishedCarDto> carsDto = carsByGame.get(gameId).stream()
                    .map(car -> new GameFinishedCarDto(car.getName(), car.getPosition(), car.isWinner()))
                    .collect(Collectors.toUnmodifiableList());

            carsByGame.put(gameId, carsDto);
        }
        return carsByGame;
    }
}
