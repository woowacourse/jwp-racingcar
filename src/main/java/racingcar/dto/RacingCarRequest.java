package racingcar.dto;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import racingcar.controller.annotation.ContainDelimiter;

@Validated
public final class RacingCarRequest {

    private static final String delimiter = ",";

    @NotBlank
    @ContainDelimiter
    private final String names;
    @NotNull
    private final int tryCount;

    public RacingCarRequest(final String names, final int count) {
        this.names = names;
        this.tryCount = count;
    }

    public List<String> sliceName(final String input) {
        validateDelimiter(input);

        List<String> splitNames = new ArrayList<>();
        addAll(splitNames, input.replace(" ", "").split(delimiter));

        return splitNames;
    }

    private static void validateDelimiter(final String input) {
        if (!input.contains(delimiter)){
            throw new IllegalArgumentException("쉼표로 이름을 구분해주세요.");
        }
    }

    public String getNames() {
        return names;
    }

    public List<String> splitNames(){
        return sliceName(names);
    }

    public int getTryCount() {
        return tryCount;
    }
}
