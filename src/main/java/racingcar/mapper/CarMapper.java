package racingcar.mapper;

import racingcar.dto.CarDto;
import racingcar.entity.CarEntity;
import racingcar.model.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {

    public static CarEntity mapCarToCarEntity(Car car) {
        return new CarEntity(null, null, car.getName(), car.getPosition());
    }

    public static List<CarEntity> mapCarsToCarEntities(List<Car> cars) {
        return cars.stream()
                .map(CarMapper::mapCarToCarEntity)
                .collect(Collectors.toList());
    }

    public static CarDto mapCarToCarDto(Car car) {
        return new CarDto(car.getName(), car.getPosition());
    }

    public static List<CarDto> mapCarsToCarDtos(List<Car> cars) {
        return cars.stream()
                .map(CarMapper::mapCarToCarDto)
                .collect(Collectors.toList());
    }

    public static CarDto mapCarEntityToCarDto(CarEntity carEntity) {
        return new CarDto(carEntity.getName(), carEntity.getPosition());
    }

    public static List<CarDto> mapCarEntitiesToCarDtos(List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(CarMapper::mapCarEntityToCarDto)
                .collect(Collectors.toList());
    }

}
