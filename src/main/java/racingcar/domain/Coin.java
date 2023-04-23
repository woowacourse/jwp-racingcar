package racingcar.domain;

public class Coin {

    private int remaining;

    public Coin(int remaining) {
        this.remaining = remaining;
    }

    public void use() {
        if (isLeft()) {
            this.remaining--;
            return;
        }
        throw new IllegalStateException("사용 불가능한 코인입니다.");
    }

    public boolean isLeft() {
        return this.remaining > 0;
    }
}
