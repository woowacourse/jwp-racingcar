package racingcar.entity;

import racingcar.model.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarEntity {

    private final Long id;
    private final Long gameId;
    private final String name;
    private final int position;

    public CarEntity(Long id, Long gameId, String name, int position) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.position = position;
    }

    public static CarEntity from(Car car){
        return new CarEntity(null, null, car.getName(), car.getPosition());
    }

    public static List<CarEntity> from(List<Car> cars) {
        return cars.stream()
                .map(CarEntity::from)
                .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
