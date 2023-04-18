package racingcar.domain.race;

import java.util.List;

public class TestNumberPicker implements NumberPicker {
    private List<Integer> numbers = List.of(4, 9, 3);
    int count = 0;

    @Override
    public int pickNumber() {
        return numbers.get(count++);
    }
}