package racingcar.domain;

public class Car {

    private final CarName carName;
    private final Position position;

    public Car(String name, int position) {
        this.carName = new CarName(name);
        this.position = new Position(position);
    }

    public void move() {
        this.position.moveForward();
    }

    public int comparePosition(Car otherCar) {
        return this.getPosition() - otherCar.getPosition();
    }

    public boolean hasSamePositionWith(Car otherCar) {
        return comparePosition(otherCar) == 0;
    }

    public int getPosition() {
        return this.position.getPosition();
    }

    public String getCarName() {
        return this.carName.getCarName();
    }
}

