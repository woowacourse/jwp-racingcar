package racingcar.dao.console;

import racingcar.dao.CarsDao;
import racingcar.dto.CarDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleCarsDao implements CarsDao {

    private final Map<Integer, CarData> carDataMap;
    private int carIdIndex;

    public ConsoleCarsDao() {
        this.carDataMap = new HashMap<>();
        carIdIndex = 1;
    }

    @Override
    public int insert(final int gameId, final String name, final int position) {
        carDataMap.put(carIdIndex, new CarData(name, position, gameId));
        carIdIndex++;
        return carIdIndex;
    }

    @Override
    public CarDto findById(final int id) {
        final CarData carData = carDataMap.get(id);
        return new CarDto(carData.getName(), carData.getPosition());
    }

    @Override
    public List<CarDto> findAllByGameId(final int gameId) {
        return carDataMap.values().stream()
                .filter(carData -> carData.getGameId() == gameId)
                .map(carData -> new CarDto(carData.getName(), carData.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
