package racingcar.dao.car;

import java.util.ArrayList;
import java.util.List;

import racingcar.dao.entity.Car;
import racingcar.dto.CarDto;

public class MemoryCarDao implements CarDao{
    private final List<Car> carTable = new ArrayList<>();

    @Override
    public void insertCar(List<CarDto> carDtos, long gameId) {
        for (CarDto carDto : carDtos) {
            carTable.add(new Car(gameId, carDto.getName(), carDto.getPosition()));
        }
    }

    @Override
    public List<Car> findAllCars() {
        return List.copyOf(carTable);
    }
}
