package racingcar.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DeterminedNumberGenerator implements NumberGenerator {

    private final Deque<Integer> numbers;

    private DeterminedNumberGenerator(Deque<Integer> numbers) {
        this.numbers = numbers;
    }

    public static DeterminedNumberGenerator createByNumbers(int... numbersToMake) {
        Deque<Integer> numbers = new ArrayDeque<>();
        for (int number : numbersToMake) {
            numbers.add(number);
        }
        return new DeterminedNumberGenerator(numbers);
    }

    public static DeterminedNumberGenerator createByList(List<Integer> numbersToMake) {
        return new DeterminedNumberGenerator(new ArrayDeque<>(numbersToMake));
    }

    public int makeDigit() {
        return numbers.pop();
    }
}
