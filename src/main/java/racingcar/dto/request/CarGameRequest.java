package racingcar.dto.request;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CarGameRequest {
    private final String names;
    private final int count;

    public CarGameRequest(String names, int count) {
        validateCount(count);
        this.names = trimName(names);
        this.count = count;
    }

    private String trimName(String names) {
        return Arrays.stream(names.split(","))
                .map(String::strip)
                .collect(Collectors.joining(","));
    }

    private void validateCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("시도횟수는 0 이상여야합니다." + System.lineSeparator() + " count : " + count);
        }
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}
