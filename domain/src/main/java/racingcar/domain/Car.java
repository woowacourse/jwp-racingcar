package racingcar.domain;

public class Car {

    private static final int GO = 4;

    private final CarName carName;
    private final Integer carId;
    private Position position;

    public Car(final String carName) {
        this(carName, 0);
    }

    public Car(final String carName, final int position) {
        this(carName, position, null);
    }

    public Car(final String carName, final int position, final Integer carId) {
        this.carName = new CarName(carName);
        this.position = new Position(position);
        this.carId = carId;
    }

    public void move(final int power) {
        if (power >= GO) {
            position = position.next();
        }
    }

    public int getPosition() {
        return position.getMoveCount();
    }

    public boolean matchPosition(final int position) {
        return this.position.isMatchMoveCount(position);
    }

    public String getCarName() {
        return carName.getName();
    }

    public Integer getCarId() {
        return carId;
    }
}
