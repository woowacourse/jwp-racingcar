package racing.dao.car;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racing.domain.Car;
import racing.dto.CarDto;

public class MemoryCarDao implements CarDao {

    private static int carId = 1;
    private static final Map<Integer, CarDto> cars = new LinkedHashMap<>();

    @Override
    public void insert(final Car car, final int gameId) {
        final CarDto currentCar = new CarDto(car);
        currentCar.setGameId(gameId);
        cars.put(carId++, currentCar);
    }

    @Override
    public List<CarDto> findByGameId(final Integer gameId) {
        return cars.values().stream()
            .filter((carDto -> carDto.getGameId() == gameId))
            .collect(Collectors.toUnmodifiableList());
    }
}
