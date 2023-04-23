package racingcar.service;

import racingcar.domain.NumberPicker;

public class StubNumberPicker implements NumberPicker {

    private final int result;

    public StubNumberPicker(final int result) {
        this.result = result;
    }

    @Override
    public int pickNumber() {
        return result;
    }
}
