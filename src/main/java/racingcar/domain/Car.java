package racingcar.domain;

import racingcar.dto.CarPositionDto;

public class Car {

    private static final int GO = 4;

    private final CarName carName;
    private Position position;

    public Car(final String carName) {
        this(carName, 0);
    }

    public Car(final String carName, final int position) {
        this.carName = new CarName(carName);
        this.position = new Position(position);
    }

    public void move(final int power) {
        if (power >= GO) {
            position = position.next();
        }
    }

    public CarPositionDto toDto() {
        return new CarPositionDto(position.getMoveCount(), carName.getName());
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
}
