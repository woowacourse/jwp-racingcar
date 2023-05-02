package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Names {

    private static final int MIN_PARTICIPANT = 2;
    private final List<Name> names = new ArrayList<>();

    public Names(final List<String> names) {
        validateDuplicateName(names);
        validateMinParticipantNumber(names);
        names.stream()
                .map(Name::new)
                .forEach(this.names::add);
    }

    private void validateDuplicateName(final List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 자동차의 이름은 중복될 수 없습니다.");
        }
    }

    private void validateMinParticipantNumber(final List<String> names) {
        if (names.size() < MIN_PARTICIPANT) {
            throw new IllegalArgumentException("[ERROR] 최소" + MIN_PARTICIPANT + "이상의 참여자가 필요합니다.");
        }
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
