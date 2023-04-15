package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Names {

    private final List<Name> names;

    public Names(final List<String> names) {
        validateDuplicatedCarNames(names);
        this.names = names.stream()
                .map(Name::of)
                .collect(Collectors.toList());
    }

    private void validateDuplicatedCarNames(final List<String> input) {
        if (input.size() != input.stream().distinct().count()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    public List<Name> getNames() {
        return new ArrayList<>(names);
    }
}
