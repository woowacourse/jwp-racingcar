package racingcar.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Names {
    private static final String NAME_DELIMITER = ",";

    private final List<String> names;

    public Names(final String namesWithDelimiter) {
        final List<String> carNames = Arrays.asList(namesWithDelimiter.split(NAME_DELIMITER));
        final List<String> trimmedCarNames = trim(carNames);
        validateNameDuplication(trimmedCarNames);
        this.names = trimmedCarNames;
    }

    private List<String> trim(final List<String> carNames) {
        return carNames.stream()
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateNameDuplication(final List<String> carsName) {
        Map<String, Integer> countByName = new HashMap<>();
        for (String carName : carsName) {
            countByName.put(carName, countByName.getOrDefault(carName, 0) + 1);
        }
        if (countByName.size() != carsName.size()) {
            throw new IllegalArgumentException("중복되는 이름이 존재합니다.");
        }
    }

    public List<String> getNames() {
        return List.copyOf(names);
    }
}
