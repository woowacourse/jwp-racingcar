package racingcar.domain;

public class Car {

    private static final int MOVE_CONDITION = 4;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private int position = 0;

    public Car(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        validateEmpty(name);
        validateLength(name);
    }

    private void validateLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름이 " + name.length() + "자 입니다. " + "이름은 5자 이하로 가능합니다.");
        }
    }

    private void validateEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    public void move(int number) {
        if (number >= MOVE_CONDITION) {
            addPosition();
        }
    }

    private void addPosition() {
        position++;
    }

    public boolean isDraw(Car other) {
        return this.position == other.position;
    }

    public Car isWin(Car other) {
        if (this.position > other.position) {
            return this;
        }
        return other;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
