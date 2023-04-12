package racingcar.domain;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Names {

    private static final String ENTER_NAME_WITH_COMMA = "쉼표로 이름을 구분해주세요.";
    private static final String COMMA = ",";
    private static final int MIN_PARTICIPANT = 2;

    private final List<Name> names = new ArrayList<>();

    public Names(final String names) {
        final List<String> splitNames = sliceNameByComma(names);
        validateMinParticipantNumber(splitNames);
        splitNames.stream()
                .map(Name::new)
                .forEach(this.names::add);
    }

    private List<String> sliceNameByComma(final String names) {
        validateComma(names);

        return getSplitName(names);
    }

    private void validateMinParticipantNumber(final List<String> names) {
        if (names.size() < MIN_PARTICIPANT) {
            throw new IllegalArgumentException("[ERROR] 최소" + MIN_PARTICIPANT + "이상의 참여자가 필요합니다.");
        }
    }

    private static List<String> getSplitName(final String names) {
        List<String> splitNames = new ArrayList<>();

        addAll(splitNames, names.split(COMMA));

        return splitNames;
    }

    private void validateComma(final String names) {
        if (!names.contains(COMMA)) {
            throw new IllegalArgumentException(ENTER_NAME_WITH_COMMA);
        }
    }

    public List<Name> getNames() {
        return Collections.unmodifiableList(names);
    }
}
