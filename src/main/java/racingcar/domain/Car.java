package racingcar.domain;

public class Car {

    private final CarName carName;
    private final Position position;

    public Car(final String name, final int position) {
        this.carName = new CarName(name);
        this.position = new Position(position);
    }

    public void move() {
        position.moveForward();
    }

    public boolean hasSamePositionWith(final Car otherCar) {
        return comparePosition(otherCar) == 0;
    }

    public int comparePosition(final Car otherCar) {
        return this.getPosition() - otherCar.getPosition();
    }

    public int getPosition() {
        return position.getPosition();
    }

    public String getCarName() {
        return carName.getCarName();
    }
}

