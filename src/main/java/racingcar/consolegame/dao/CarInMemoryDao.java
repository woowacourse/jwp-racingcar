package racingcar.consolegame.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import racingcar.dao.CarDao;
import racingcar.dao.CarEntity;
import racingcar.dto.CarDto;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/04/18
 */
public class CarInMemoryDao implements CarDao {

    private final Map<Integer, List<CarEntity>> cars = new HashMap<>();
    private int id = 1;

    @Override
    public void insertCar(final CarDto car, final int gameId) {
        if (cars.containsKey(gameId)) {
            cars.get(gameId).add(new CarEntity(id++, car.getName(), car.getPosition()));
        } else {
            List<CarEntity> newCars = new ArrayList<>();
            newCars.add(new CarEntity(id++, car.getName(), car.getPosition()));
            cars.put(gameId, newCars);
        }
    }

    @Override
    public List<CarDto> findCarsByRacingGameId(final int gameId) {
        for (Entry<Integer, List<CarEntity>> carEntries : cars.entrySet()) {
            if (carEntries.getKey() == gameId) {
                return carEntries.getValue().stream()
                        .map(carEntity -> CarDto.of(carEntity.getName(), carEntity.getPosition()))
                        .collect(Collectors.toList());
            }
        }
        throw new IllegalArgumentException("게임 아이디를 확인해주세요.");
    }

}
