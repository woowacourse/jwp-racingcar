package racingcar.domain;

public class Car {

    private static final int BOUNDARY = 4;

    private final CarName carName;
    private final Position position;

    public Car(final String carName, final int position) {
        this.carName = new CarName(carName);
        this.position = new Position(position);
    }

    public Car(String name) {
        this(name, 0);
    }

    public void move(int number) {
        if (number >= BOUNDARY) {
            this.position.move();
        }
    }

    public String getCarName() {
        return carName.getCarName();
    }

    public int getPosition() {
        return position.getPosition();
    }
}
