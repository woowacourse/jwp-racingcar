package racingcar.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class NameSeparator {
    public List<String> separateBy(String delimiter, String names) {
        return Arrays.asList(names.split(delimiter, -1));
    }
}
