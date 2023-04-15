package racingcar.domain;

public class Lap {
    private int lap;

    public Lap(int lap) {
        this.lap = lap;
    }

    public void reduce() {
        lap--;
    }

    public boolean isPlayable() {
        return lap > 0;
    }
}
