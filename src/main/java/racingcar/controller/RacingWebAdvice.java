package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.exception.InvalidTrialTimesFormatException;

@RestControllerAdvice
public class RacingWebAdvice {

    private static final String DUPLICATE_CAR_NAMES_ERROR_MESSAGE = "중복된 차 이름이 존재합니다.";
    private static final String EXCEED_CAR_NAME_LENGTH_ERROR_MESSAGE = "자동차 이름은 다섯 글자 이하여야 합니다.";
    private static final String INVALID_CAR_NAME_FORMAT_ERROR_MESSAGE = "자동차 이름은 문자와 숫자만 가능합니다.";
    private static final String INVALID_RANGE_TRIAL_TIMES_ERROR_MESSAGE = "시도 횟수는 1 이상 100 이하여야 합니다.";
    private static final String INVALID_TRIAL_TIMES_FORMAT_ERROR_MESSAGE = "시도 횟수는 숫자만 입력 가능합니다.";

    @ExceptionHandler(DuplicateCarNamesException.class)
    public ResponseEntity<ExceptionInfo> duplicateCarNamesHandler() {
        return ResponseEntity.badRequest().body(new ExceptionInfo(DUPLICATE_CAR_NAMES_ERROR_MESSAGE));
    }

    @ExceptionHandler(ExceedCarNameLengthException.class)
    public ResponseEntity<ExceptionInfo> exceedCarNameLengthHandler() {
        return ResponseEntity.badRequest().body(new ExceptionInfo(EXCEED_CAR_NAME_LENGTH_ERROR_MESSAGE));
    }

    @ExceptionHandler(InvalidCarNameFormatException.class)
    public ResponseEntity<ExceptionInfo> invalidCarNameFormatHandler() {
        return ResponseEntity.badRequest().body(new ExceptionInfo(INVALID_CAR_NAME_FORMAT_ERROR_MESSAGE));
    }

    @ExceptionHandler(InvalidRangeTrialTimesException.class)
    public ResponseEntity<ExceptionInfo> invalidRangeTrialTimesHandler() {
        return ResponseEntity.badRequest().body(new ExceptionInfo(INVALID_RANGE_TRIAL_TIMES_ERROR_MESSAGE));
    }

    @ExceptionHandler(InvalidTrialTimesFormatException.class)
    public ResponseEntity<ExceptionInfo> invalidTrialTimesFormatHandler() {
        return ResponseEntity.badRequest().body(new ExceptionInfo(INVALID_TRIAL_TIMES_FORMAT_ERROR_MESSAGE));
    }

    static class ExceptionInfo {
        private final String message;

        public ExceptionInfo(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
