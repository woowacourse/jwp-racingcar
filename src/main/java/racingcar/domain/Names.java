package racingcar.domain;

import racingcar.error.ErrorMessage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Names {
    private final List<Name> names;

    private Names(List<Name> names) {
        this.names = names;
    }

    public static Names from(List<String> names) {
        validateDuplication(names);

        List<Name> convertedNames = names.stream()
                .map(Name::from)
                .collect(Collectors.toList());

        return new Names(convertedNames);
    }

    private static void validateDuplication(List<String> names) {
        int countAfterRemovingDuplicateNames = (int) names.stream()
                .distinct()
                .count();

        if (countAfterRemovingDuplicateNames != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NAMES.getValue());
        }
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
