package racingcar.domain;

public class Car {

    private static final int MOVING_STANDARD = 4;

    private final CarName name;
    private int position = 0;

    public Car(final String name) {
        this.name = new CarName(name);
    }

    public void drive(int number) {
        if (MOVING_STANDARD <= number) {
            position += 1;
        }
    }

    public boolean hasPosition(int position) {
        return this.position == position;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name.getName();
    }
}
