package racingcar.domain;

public class Position {
    private int value;

    public Position(int value) {
        this.value = value;
    }

    public void increaseDistance() {
        this.value++;
    }

    public int getValue() {
        return value;
    }
}
