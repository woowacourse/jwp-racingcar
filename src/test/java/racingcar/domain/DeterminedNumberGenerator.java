package racingcar.domain;

import java.util.List;

public class DeterminedNumberGenerator implements NumberGenerator {

    private List<Integer> repository;
    private int index = 0;

    public void readRepository(List<Integer> repository) {
        this.repository = repository;
    }

    public int makeDigit() {
        return repository.get(index++);
    }
}
