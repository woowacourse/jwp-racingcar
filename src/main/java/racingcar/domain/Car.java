package racingcar.domain;

public final class Car {

    private final Name name;
    private final Position position;
    private final boolean isWinner;

    public Car(final Name name) {
        this(name, Position.create());
    }

    public Car(final Name name, final Position position) {
        this.name = name;
        this.position = position;
        this.isWinner = false;
    }

    public Car(final Car car) {
        this(car, false);
    }

    public Car(final Car car, final boolean isWinner) {
        this.name = Name.of(car.getNameValue());
        this.position = new Position(car.getPosition());
        this.isWinner = isWinner;
    }

    public void move(final boolean movable) {
        if (movable) {
            position.move();
        }
    }

    public Position getPosition() {
        return position;
    }

    public int getPositionValue() {
        return position.getPosition();
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return name.getName();
    }

    public boolean isWinner() {
        return isWinner;
    }

}
