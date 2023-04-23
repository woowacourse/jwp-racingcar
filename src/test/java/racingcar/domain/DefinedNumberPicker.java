package racingcar.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DefinedNumberPicker implements NumberPicker {

    private final Queue<Integer> integers;

    public DefinedNumberPicker(final List<Integer> integers) {
        this.integers = new LinkedList<>(integers);
    }

    @Override
    public int pickNumber() {
        return integers.poll();
    }
}
