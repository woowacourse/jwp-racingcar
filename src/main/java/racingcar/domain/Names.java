package racingcar.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Names {
    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "입력값은 비어있을 수 없습니다.";
    public static final String DUPLICATING_NAME_EXCEPTION_MESSAGE = "중복된 이름은 사용할 수 없습니다.";
    private final List<Name> names;

    private Names(List<Name> names) {
        this.names = names;
    }

    public static Names of(List<Name> names) {
        validateCarNames(names);
        return new Names(names);
    }

    private static void validateCarNames(List<Name> names) {
        isEmpty(names);
        isDuplicating(names);
    }

    private static void isDuplicating(List<Name> names) {
        Set<Name> namesWithoutDuplication = new HashSet<>(names);
        if (names.size() != namesWithoutDuplication.size()) {
            throw new IllegalArgumentException(DUPLICATING_NAME_EXCEPTION_MESSAGE);
        }
    }

    private static void isEmpty(List<Name> names) {
        if (names.size() == 0) {
            throw new IllegalArgumentException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public Iterator<Name> nameIterator() {
        return names.stream().iterator();
    }
}
