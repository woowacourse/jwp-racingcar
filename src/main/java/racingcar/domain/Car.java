package racingcar.domain;

public class Car {
    private static final int DEFAULT_POSITION = 0;
    private static final int MOVE_CRITERIA = 4;

    private final Name name;
    private final Position position;

    private Car(Name name, Position position) {
        this.name = name;
        this.position = position;
    }

    public static Car from(String name) {
        return new Car(new Name(name), new Position(DEFAULT_POSITION));
    }

    public void runForward(int engine) {
        if (engine >= MOVE_CRITERIA) {
            position.increasePosition();
        }
    }

    public boolean isSamePosition(int otherPosition) {
        return otherPosition == position.getValue();
    }

    public String getNameValue() {
        return name.getValue();
    }

    public int getPositionValue() {
        return position.getValue();
    }
}
