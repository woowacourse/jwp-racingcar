package racingcar.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CarGameRequest {

    @NotNull
    private final String names;

    @Positive
    private final Integer count;

    public CarGameRequest(String names, Integer count) {
        this.names = trimName(names);
        this.count = count;
    }

    private String trimName(String names) {
        return Arrays.stream(names.split(","))
                .map(String::strip)
                .collect(Collectors.joining(","));
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}
