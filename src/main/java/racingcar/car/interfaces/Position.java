package racingcar.car.interfaces;

public interface Position extends Comparable<Position> {
    
    Position add(int value);
    
    int getValue();
}
