package racingcar.domain;

public class Coin {

    private int remaining;

    public Coin(int remaining) {
        this.remaining = remaining;
    }

    public void use() {
        if (isLeft()) {
            this.remaining--;
        }
    }

    public boolean isLeft() {
        return this.remaining > 0;
    }
}
