package racingcar.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

public class RacingStartRequest {

    @NotEmpty(message = "[ERROR] Request message`s names must not be Empty.")
    private List<String> names;
    @NotNull(message = "[ERROR] Request message`s count must not be null.")
    private Integer count;

    public RacingStartRequest(String names, Integer count) {
        this.names = Arrays.asList(names.strip().split(","));
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
