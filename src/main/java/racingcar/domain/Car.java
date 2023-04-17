package racingcar.domain;

public final class Car {

    private Long id;
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
        this.name = new Name(car.getNameValue());
        this.position = car.getPosition();
        this.isWinner = isWinner;
    }


    public Car(final Long gameId, final String nameValue, final int positionValue, final boolean winner) {
        this.id = gameId;
        this.name = new Name(nameValue);
        this.position = new Position(positionValue);
        this.isWinner = winner;
    }

    public void move(final boolean movable) {
        if (movable) {
            position.move();
        }
    }

    public Long getId() {
        return id;
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
