package racingcar.domain;

public class Coin {

    private final int given;
    private int used;

    public Coin(final int given) {
        this.given = given;
        this.used = 0;
    }

    public void use() {
        if (isLeft()) {
            used++;
        }
    }

    public boolean isLeft() {
        return used < given;
    }

    public int getGiven() {
        return given;
    }
}
