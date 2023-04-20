package racingcar.dao.console;

import racingcar.dao.CarDao;
import racingcar.dao.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleCarDao implements CarDao {

    private static final Integer START_ID = 1;
    List<CarEntity> cars = new ArrayList<>();
    private int serialNumber = START_ID;

    @Override
    public List<Integer> saveAll(final List<CarEntity> carEntities) {
        List<Integer> carIds = new ArrayList<>();
        for(CarEntity carEntity : carEntities) {
            int carId = serialNumber;
            CarEntity car = new CarEntity(carId, carEntity.getName(), carEntity.getPosition(), carEntity.getGameId());
            cars.add(car);
            carIds.add(carId);
            serialNumber++;
        }
        return carIds;
    }

    @Override
    public List<CarEntity> findCarsByGameID(final int gameId) {
        return cars.stream()
                .filter(car -> car.getGameId() == gameId)
                .collect(Collectors.toList());
    }

}