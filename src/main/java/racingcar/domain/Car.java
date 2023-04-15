package racingcar.domain;

public class Car implements Comparable<Car> {

    private static final int CAR_FORWARD_NUMBER = 4;

    private final CarName name;
    private final CarPosition position;

    private Car(final String name) {
        this.name = CarName.create(name);
        this.position = CarPosition.create();
    }

    private Car(CarName name, CarPosition position) {
        this.name = name;
        this.position = position;
    }

    public static Car create(final String name) {
        return new Car(name);
    }

    public Car move(final int power) {
        if (power >= CAR_FORWARD_NUMBER) {
            final CarPosition newPosition = position.addPosition();
            return new Car(name, newPosition);
        }
        return new Car(name, position);
    }

    public boolean isSamePosition(final Car diffCar) {
        return position.equals(diffCar.getCarPosition());
    }

    @Override
    public int compareTo(final Car diffCar) {
        return position.compareTo(diffCar.getCarPosition());
    }

    public CarName getCarName() {
        return name;
    }

    public String getName() {
        return name.getName();
    }

    public CarPosition getCarPosition() {
        return position;
    }

    public int getPosition() {
        return position.getPosition();
    }
}
