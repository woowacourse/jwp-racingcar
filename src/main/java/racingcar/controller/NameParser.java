package racingcar.controller;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import racingcar.exception.DelimiterFoundException;

@Component
public class NameParser {

    public List<String> slice(final String names) {
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
}
