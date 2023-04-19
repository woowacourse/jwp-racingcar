package racingcar.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public class GameRequest {

    @NotEmpty
    private final List<@NotBlank(message = "공백은 입력할 수 없습니다. 입력 값 : ${validatedValue}") String> names;
    @Positive(message = "1 미만의 값은 입력할 수 없습니다. 입력 값 : ${validatedValue}")
    private final int count;

    public GameRequest(final List<String> names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
