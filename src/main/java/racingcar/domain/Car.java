package racingcar.domain;

public class Car {

    private static final int CAR_FORWARD_NUMBER = 4;

    private final CarName name;
    private CarPosition position;

    public Car(final CarName name, final CarPosition position) {
        this.name = name;
        this.position = position;
    }

    public void move(final int power) {
        if (power >= CAR_FORWARD_NUMBER) {
            this.position = position.addPosition();
        }
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
