package racingcar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

public class RaceRequest {

    private static final String SPLIT_DELIMITER = ",";

    @NotBlank(message = "자동차 이름은 비어있을 수 없습니다.")
    private final String names;

    @NotNull(message = "경주 횟수는 비어있을 수 없습니다.")
    private final int count;

    public RaceRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> makeSplitNames() {
        return Arrays.asList(names.split(SPLIT_DELIMITER));
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
