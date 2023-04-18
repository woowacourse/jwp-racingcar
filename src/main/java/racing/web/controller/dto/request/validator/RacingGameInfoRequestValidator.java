package racing.web.controller.dto.request.validator;

import static racing.common.exception.BusinessExceptionType.CAR_NAME_BLANK;
import static racing.common.exception.BusinessExceptionType.CAR_NAME_DUPLICATION;
import static racing.common.exception.BusinessExceptionType.CAR_NAME_INVALID_RANGE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import racing.common.exception.BusinessException;
import racing.web.controller.dto.request.RacingGameInfoRequest;

public class RacingGameInfoRequestValidator implements RequestValidator<RacingGameInfoRequest> {

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
            throw new BusinessException(CAR_NAME_DUPLICATION);
        }
    }

    private void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new BusinessException(CAR_NAME_BLANK);
        }
    }

    private void validateLengthInRange(String name) {
        if (name.length() < 1 || 5 < name.length()) {
            throw new BusinessException(CAR_NAME_INVALID_RANGE);
        }
    }
}
