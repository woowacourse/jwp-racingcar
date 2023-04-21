package racingcar.controller.dto;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.List;
import racingcar.exception.DelimiterFoundException;

public class GameRequest {

    private final String names;
    private final int count;

    public GameRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> sliceNames() {
        validateDelimiter(names);

        return getSplitName(names);
    }

    private void validateDelimiter(final String names) {
        final String delimiter = ",";

        if (!names.contains(delimiter)) {
            throw new DelimiterFoundException(delimiter);
        }
    }

    private List<String> getSplitName(final String names) {
        List<String> splitNames = new ArrayList<>();
        final String delimiter = ",";
        addAll(splitNames, names.split(delimiter));

        return splitNames;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
