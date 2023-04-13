package racingcar.domain;

public class CarPosition {

    private static final int INIT_POSITION = 1;

    private int position;

    private CarPosition(final int position) {
        this.position = position;
    }

    public static CarPosition create() {
        return new CarPosition(INIT_POSITION);
    }

    public void addPosition() {
        position++;
    }

    public int getPosition() {
        return position;
    }
}
