package racingcar.car.repository;

import java.util.List;
import racingcar.car.model.Car;

public interface CarDAO {
    
    void insert(final Car car, final int gameId);
    
    void insertAll(List<Car> cars, final int gameId);
    
    Car findByName(final String name, final int gameId);
    
    List<Car> findAll(final int gameId);
    
}
