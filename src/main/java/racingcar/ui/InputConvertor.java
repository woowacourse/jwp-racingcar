package racingcar.ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.dto.RacingCarRequestDto;

public class InputConvertor {

    private static final String DELIMITER = ",";

    private InputConvertor() {
    }

    public static List<String> carNames(final RacingCarRequestDto request) {
        String names = request.getNames();
        if (!names.contains(DELIMITER)) {
            throw new IllegalArgumentException(" 자동차 이름은 구분자(" + DELIMITER + ")로 구분해야 합니다.");
        }
        return Arrays.stream(names.split(DELIMITER))
            .map(String::strip)
            .collect(Collectors.toList());
    }

    public static int tryCount(final RacingCarRequestDto request) {
        return Integer.parseInt(request.getCount());
    }
}
