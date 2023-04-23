package racingcar.car.model;

import java.util.Objects;

public class RacingCar implements Car {
    
    public static final int MOVE_DISTANCE = 1;
    private static final int MIN_MOVING_NUM = 4;
    private final Name name;
    private final Position position;
    
    public RacingCar(final Name name, final Position position) {
        this.name = name;
        this.position = position;
    }
    
    public static Car create(final String name, final int startPosition) {
        return new RacingCar(CarName.create(name), CarPosition.create(startPosition));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final RacingCar racingCar = (RacingCar) o;
        return this.name.equals(racingCar.name);
    }
    
    @Override
    public int compareTo(final Car o) {
        return this.position.compareTo(o.getPosition());
    }
    
    @Override
    public Car move(final int fuel) {
        if (fuel >= MIN_MOVING_NUM) {
            return new RacingCar(this.name, this.position.add(MOVE_DISTANCE));
        }
        return this;
    }
    
    @Override
    public boolean isSamePositionTo(final Car car) {
        return this.position.equals(car.getPosition());
    }
    
    public Name getName() {
        return this.name;
    }
    
    public Position getPosition() {
        return this.position;
    }
}
