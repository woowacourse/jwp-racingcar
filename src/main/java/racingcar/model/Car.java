package racingcar.model;


public class Car implements Comparable<Car> {
    private static final int CAR_INITIAL_LENGTH = 0;
    private static final int CAR_MOVE_STANDARD_LENGTH = 4;

    private final Name name;
    private final Position position;

    public Car(String name) {
        this.name = new Name(name);
        this.position = new Position(CAR_INITIAL_LENGTH);
    }

    public void moveByNumber(int moveForNum) {
        if (moveForNum >= CAR_MOVE_STANDARD_LENGTH) {
            position.moveCarPosition();
        }
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    public boolean checkPositionEqual(Car car) {
        return position.checkEqual(car.position);
    }

    @Override
    public int compareTo(Car car) {
        return position.getPosition() - car.getPosition();
    }
}
