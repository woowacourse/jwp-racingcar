package racingcar.domain;

public final class Car {
    private static final int MIN_MOVABLE_VALUE = 4;
    private static final int MAX_MOVABLE_VALUE = 9;

    private final Name name;
    private Position position;

    public Car(final String nameValue) {
        this.name = new Name(nameValue);
        this.position = Position.ZERO;
    }

    public void move(final NumberGenerator numberGenerator) {
        final int number = numberGenerator.generate();
        if (isMovable(number)) {
            position = position.move();
        }
    }

    private boolean isMovable(final int number) {
        return MIN_MOVABLE_VALUE <= number && number <= MAX_MOVABLE_VALUE;
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public String getNameValue() {
        return name.getValue();
    }

    public int getPositionValue() {
        return position.getValue();
    }
}
