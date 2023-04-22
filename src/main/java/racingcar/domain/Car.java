package racingcar.domain;

public class Car {
    private static final int DISTANCE_DEFAULT = 0;
    private static final int MOVE_CRITERIA = 4;

    private final Name name;
    private final Position position;

    Car(String name, int distance) {
        this.name = new Name(name);
        this.position = new Position(distance);
    }

    public Car(String name) {
        this(name, DISTANCE_DEFAULT);
    }

    public void runForward(int engine) {
        if (engine >= MOVE_CRITERIA) {
            position.increaseDistance();
        }
    }

    public Position getDistance() {
        return position;
    }

    public Name getName() {
        return name;
    }

    public boolean isSameDistance(int otherDistance) {
        return otherDistance == position.getValue();
    }
}
