package racingcar.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class GamePlayRequestDto {
    @NotEmpty(message = "차 이름을 입력해주세요.")
    private final List<String> names;

    @Range(min = 1, max = 10000, message = "{min}이상, {max}이하의 시도 횟수를 입력해주세요.")
    private final int count;

    public GamePlayRequestDto(final List<String> names, final int count) {
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
