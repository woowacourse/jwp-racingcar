package racingcar.car.model;

public interface Position extends Comparable<Position> {
    
    Position add(int value);
    
    int getValue();
}
