package racingcar.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class RaceRequest {

    private static final String SPLIT_DELIMITER = ",";

    @Length(min = 1, max = 1000, message = "글자 수가 초과했습니다.")
    private String names;

    @Max(value = 100, message = "횟수는 100회 이하만 가능합니다.")
    @Min(value = 1, message = "횟수는 1회 이상만 가능합니다.")
    private int count;

    public RaceRequest() {
    }

    public RaceRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getCarNames() {
        final String[] splitNames = names.split(SPLIT_DELIMITER);
        return Arrays.stream(splitNames).collect(Collectors.toList());
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
