package racingcar.domain;

public class Coin {

    private final int given;
    private int remaining;

    public Coin(int given) {
        this.given = given;
        this.remaining = given;
    }

    public void use() {
        if (isLeft()) {
            remaining--;
        }
    }

    public boolean isLeft() {
        return remaining > 0;
    }

    public int getGiven() {
        return given;
    }
}
