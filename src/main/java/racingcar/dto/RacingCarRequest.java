package racingcar.dto;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.List;

public final class RacingCarRequest {

    private static final String ENTER_NAME_WITH_COMMA = "쉼표로 이름을 구분해주세요.";
    private static final String COMMA = ",";

    private final List<String> names;
    private final int tryCount;

    public RacingCarRequest(final String names, final int count) {
        this.names = sliceNameByComma(names);
        this.tryCount = count;
    }

    private List<String> sliceNameByComma(final String names) {
        validateComma(names);

        return getSplitName(names);
    }

    private static List<String> getSplitName(final String names) {
        List<String> splitNames = new ArrayList<>();

        addAll(splitNames, names.replace(" ", "").split(COMMA));

        return splitNames;
    }

    private void validateComma(final String names) {
        if (!names.contains(COMMA)) {
            throw new IllegalArgumentException(ENTER_NAME_WITH_COMMA);
        }
    }

    public List<String> getNames() {
        return names;
    }

    public int getTryCount() {
        return tryCount;
    }
}
