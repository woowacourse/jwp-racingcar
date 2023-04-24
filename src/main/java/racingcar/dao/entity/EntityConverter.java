package racingcar.dao.entity;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class EntityConverter {

    public static Car toDomainEntity(CarEntity car) {
        return Car.of(car.getPlayRecordId(), car.getName(), car.getPosition());
    }

    public static CarEntity toDaoEntity(Car car) {
        return new CarEntity(car.getPlayRecordId(), car.getName(), car.getPosition());
    }

    public static List<CarEntity> toDaoEntities(List<Car> cars) {
        return cars.stream()
                .map(EntityConverter::toDaoEntity)
                .collect(Collectors.toList());
    }

    public static List<Car> toDomainEntities(final List<CarEntity> cars) {
        return cars.stream()
                .map(EntityConverter::toDomainEntity)
                .collect(Collectors.toList());
    }
}
