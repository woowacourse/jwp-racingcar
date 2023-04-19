package racingcar.car.interfaces;

public interface Car extends Comparable<Car> {
    
    Car move(int fuel);
    
    boolean isSamePositionTo(Car car);
    
    Name getName();
    
    Position getPosition();
    
}
