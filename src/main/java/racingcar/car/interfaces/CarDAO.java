package racingcar.car.interfaces;

import java.util.List;

public interface CarDAO {
    
    void insert(final Car car, final int gameId);
    
    void insertAll(List<Car> cars, final int gameId);
    
    Car findByName(final String name, final int gameId);
    
    List<Car> findAll(final int gameId);
    
}
