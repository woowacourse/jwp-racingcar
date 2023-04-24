package racingcar.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameRequest {
    private static final String NAME_DELIMITER = ",";

    @NotEmpty
    private String names;
    @NotNull
    private Integer count;

    public GameRequest(String names, Integer count) {
        this.names = names;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public List<String> getNames() {
        return Arrays.stream(names.split(NAME_DELIMITER))
                .collect(Collectors.toList());
    }
}
