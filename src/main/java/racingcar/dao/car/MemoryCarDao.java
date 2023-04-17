package racingcar.dao.car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import racingcar.dto.CarDto;

public class MemoryCarDao implements CarDao{
    private final Map<Long, List<CarDto>> carTable = new HashMap<>();

    @Override
    public void insertCar(List<CarDto> carDtos, long gameId) {
        carTable.put(gameId, carDtos);
    }

    @Override
    public List<CarDto> findCarsInfoByGameId(Long gameId) {
        return carTable.get(gameId);
    }
}
