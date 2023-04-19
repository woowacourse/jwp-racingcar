package racingcar.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarGameRequest {
    @NotEmpty
    private String names;
    @NotNull
    private Integer count;

    public CarGameRequest(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getNames() {
        return names;
    }
}
