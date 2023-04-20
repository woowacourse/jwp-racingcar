package racingcar.domain.car;

public class Car {
    private static final int LEAST_CONDITION = 4;
    private static final int MAX_NAME_LENGTH = 5;
    private static final Position ONE_STEP = new Position(1);

    private final String name;
    private Position position;

    public Car(String name) {
        validate(name);
        this.name = name;
        this.position = new Position();
    }

    public Car(String name, int position) {
        this.name = name;
        this.position = new Position(position);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position.getValue();
    }

    private void validate(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차의 이름은 다섯 글자 이하여야합니다.");
        }
    }

    public void moveDependingOn(int power) {
        if (power >= LEAST_CONDITION) {
            this.position = position.add(ONE_STEP);
        }
    }

    public boolean isInSamePosition(Position other) {
        return this.position.equals(other);
    }

}
