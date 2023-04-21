package racingcar.domain;

public class Car {

    private static final int DRIVING_DISTANCE = 1;
    private static final int MOVING_STANDARD = 4;

    private final CarName name;
    private int drivenDistance = 0;

    public Car(final String name) {
        this.name = new CarName(name);
    }

    public void drive(int number) {
        if (MOVING_STANDARD <= number) {
            drivenDistance += DRIVING_DISTANCE;
        }
    }

    public int getDrivenDistance() {
        return drivenDistance;
    }

    public String getName() {
        return name.getName();
    }
}
