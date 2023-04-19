package racingcar.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarGameRequest {

    @NotNull
    private final List<String> names;

    @Positive
    private final Integer count;

    public CarGameRequest(String names, Integer count) {
        this.names = trimName(names);
        this.count = count;
    }

    private List<String> trimName(String names) {
        return Arrays.stream(names.split(","))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public int getCount() {
        return count;
    }

    public List<String> getNames() {
        return names;
    }
}
