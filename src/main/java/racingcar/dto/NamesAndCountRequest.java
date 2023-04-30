package racingcar.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NamesAndCountRequest {

    public static final String SEPARATOR = ",";

    @NotNull(message = "이름을 입력해주세요.")
    private String names;
    @NotNull(message = "시도 횟수를 입력해주세요.")
    @Positive(message = "양의 정수 값을 입력해주세요.")
    private int count;

    public NamesAndCountRequest() {
    }

    public NamesAndCountRequest(final String names, final int count) {
        this.names = names;
        this.count = count;
    }

    public List<String> getNames() {
        return List.of(names.split(SEPARATOR));
    }

    public int getCount() {
        return count;
    }
}
