package racingcar.car.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import racingcar.car.model.Car;

public class RacingCarInMemoryDAO implements CarDAO {
    
    public static final String NO_SUCH_CAR_NAME_ERROR = "해당 이름의 자동차가 없습니다.";
    private final Map<Integer, List<Car>> carRepository = new HashMap<>();
    
    @Override
    public void insert(final Car car, final int gameId) {
        if (!this.carRepository.containsKey(gameId)) {
            this.carRepository.put(gameId, List.of(car));
            return;
        }
        this.carRepository.get(gameId).add(car);
    }
    
    @Override
    public void insertAll(final List<Car> cars, final int gameId) {
        this.carRepository.put(gameId, cars);
    }
    
    @Override
    public Car findByName(final String name, final int gameId) {
        return this.carRepository.get(gameId).stream()
                .filter(car -> Objects.equals(car.getName().getValue(), name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_SUCH_CAR_NAME_ERROR));
    }
    
    @Override
    public List<Car> findAll(final int gameId) {
        return this.carRepository.get(gameId);
    }
}
