package racingcar.domain;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Names {
    private static final String DELIMITER = ",";
    private static final int MIN_PARTICIPANT = 2;

    private final List<Name> names = new ArrayList<>();

    public Names(final String names) {
        final List<String> splitNames = validateAndSplit(names);
        splitNames.stream()
                .map(Name::new)
                .forEach(this.names::add);
    }

    private List<String> validateAndSplit(final String names) {
        validateNotNull(names);
        final List<String> splitNames = splitNames(names);
        validateMinParticipantNumber(splitNames);
        validateDuplicateName(splitNames);
        return splitNames;
    }

    private void validateNotNull(final String names) {
        if (names == null) {
            throw new IllegalArgumentException("[ERROR] 이름이 없습니다.");
        }
    }

    private void validateMinParticipantNumber(final List<String> names) {
        if (names.size() < MIN_PARTICIPANT) {
            throw new IllegalArgumentException("[ERROR] 최소" + MIN_PARTICIPANT + "이상의 참여자가 필요합니다.");
        }
    }

    private void validateDuplicateName(final List<String> splitNames) {
        if (splitNames.stream().distinct().count() != splitNames.size()) {
            throw new IllegalArgumentException("[ERROR] 자동차의 이름은 중복될 수 없습니다.");
        }
    }

    private List<String> splitNames(final String names) {
        List<String> splitNames = new ArrayList<>();

        addAll(splitNames, names.split(DELIMITER));

        return splitNames;
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
