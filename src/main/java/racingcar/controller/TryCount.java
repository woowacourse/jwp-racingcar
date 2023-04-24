package racingcar.controller;

public class TryCount {

    private static final int MAX_TRY_COUNT = 10;

    private int value = 0;

    public void increase() {
        value++;
    }

    public boolean isRunnable() {
        return value < MAX_TRY_COUNT;
    }
}
