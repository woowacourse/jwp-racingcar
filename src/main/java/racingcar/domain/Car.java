package racingcar.domain;

public class Car {
    private static final int POSITION_DEFAULT = 0;
    private static final int MOVE_CRITERIA = 4;

    private final Name name;
    private final Position position;

    public Car(String name) {
        this(name, POSITION_DEFAULT);
    }

    public Car(String name, int position) {
        this.name = new Name(name);
        this.position = new Position(position);
    }

    public void runForward(int engine) {
        if (engine >= MOVE_CRITERIA) {
            position.increasePosition();
        }
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isSamePosition(int otherPosition) {
        return otherPosition == position.getValue();
    }
}
