package racingcar.domain;

public class Car implements Comparable<Car> {

    private static final int CAR_FORWARD_NUMBER = 4;

    private final CarName name;
    private final CarPosition position;

    private Car(final CarName name, final CarPosition position) {
        this.name = name;
        this.position = position;
    }

    public static Car create(final CarName name, final CarPosition position) {
        return new Car(name, position);
    }

    public Car move(final int power) {
        if (power >= CAR_FORWARD_NUMBER) {
            final CarPosition newPosition = position.addPosition();
            return new Car(name, newPosition);
        }
        return new Car(name, position);
    }

    public boolean isSamePosition(final Car diffCar) {
        return position.getPosition() == diffCar.position.getPosition();
    }

    @Override
    public int compareTo(Car diffCar) {
        return position.getPosition() - diffCar.position.getPosition();
    }

    public CarPosition getCarPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }
}
