package racingcar.car.model;

public class Car {
    
    private static final int MIN_MOVING_NUM = 4;
    private final String name;
    private int position;
    
    public Car(final String name, final int startPosition) {
        this.name = name;
        this.position = startPosition;
    }
    
    public boolean canMoving(final int randomValue) {
        return randomValue >= MIN_MOVING_NUM;
    }
    
    public void move(final boolean isMoving) {
        if (isMoving) {
            this.position++;
        }
    }
    
    public Car getLargerCar(final Car compareCar) {
        if (this.position > compareCar.getPosition()) {
            return this;
        }
        return compareCar;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public boolean isSamePositionCar(final Car maxPositionCar) {
        return this.position == maxPositionCar.getPosition();
    }
    
    public String getName() {
        return this.name;
    }
}
