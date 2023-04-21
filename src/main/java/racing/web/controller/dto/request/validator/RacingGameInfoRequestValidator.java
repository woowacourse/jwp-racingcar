package racing.web.controller.dto.request.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import racing.web.controller.dto.request.RacingGameInfoRequest;

public class RacingGameInfoRequestValidator implements RequestValidator<RacingGameInfoRequest> {
    private static final String CAR_NAME_BLANK = "자동차 이름이 비어있습니다.";
    private static final String CAR_NAME_INVALID_RANGE = "자동차 이름은 1 ~ 5자 사이 이어야 합니다.";
    private static final String CAR_NAME_DUPLICATED = "중복된 자동차 이름이 존재합니다.";
    private static final String DELIMITER = ",";

    @Override
    public void validate(RacingGameInfoRequest requestDto) {
        validateName(requestDto.getNames());
    }

    private void validateName(String nameRequest) {
        List<String> names = Arrays.stream(nameRequest.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());

        for (String name : names) {
            validateNotBlank(name);
            validateLengthInRange(name);
        }

        validateDuplication(names);
    }

    private void validateDuplication(List<String> names) {
        int distinctSize = new HashSet<>(names).size();

        if (names.size() != distinctSize) {
            throw new IllegalArgumentException(CAR_NAME_DUPLICATED);
        }
    }

    private void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(CAR_NAME_BLANK);
        }
    }

    private void validateLengthInRange(String name) {
        if (name.length() < 1 || 5 < name.length()) {
            throw new IllegalArgumentException(CAR_NAME_INVALID_RANGE);
        }
    }
}
