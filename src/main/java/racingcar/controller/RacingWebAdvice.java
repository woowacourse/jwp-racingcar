package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.ExceptionInfo;
import racingcar.exception.HasBlankCarNameException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.exception.InvalidTrialTimesFormatException;

@RestControllerAdvice
public class RacingWebAdvice {

    private static final String ERROR_PREFIX = "[ERROR] ";

    @ExceptionHandler(DuplicateCarNamesException.class)
    public ResponseEntity<ExceptionInfo> duplicateCarNamesHandler() {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(ERROR_PREFIX + "중복된 차 이름이 존재합니다.");
        return exceptionInfo.makeErrorResponse();
    }

    @ExceptionHandler(ExceedCarNameLengthException.class)
    public ResponseEntity<ExceptionInfo> exceedCarNameLengthHandler() {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(ERROR_PREFIX + "자동차 이름은 다섯 글자 이하여야 합니다.");
        return exceptionInfo.makeErrorResponse();
    }

    @ExceptionHandler(InvalidCarNameFormatException.class)
    public ResponseEntity<ExceptionInfo> invalidCarNameFormatHandler() {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(ERROR_PREFIX + "자동차 이름은 문자와 숫자만 가능합니다.");
        return exceptionInfo.makeErrorResponse();
    }

    @ExceptionHandler(InvalidRangeTrialTimesException.class)
    public ResponseEntity<ExceptionInfo> invalidRangeTrialTimesHandler() {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(ERROR_PREFIX + "시도 횟수는 1 이상 100 이하여야 합니다.");
        return exceptionInfo.makeErrorResponse();
    }

    @ExceptionHandler(InvalidTrialTimesFormatException.class)
    public ResponseEntity<ExceptionInfo> invalidTrialTimesFormatHandler() {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(ERROR_PREFIX + "시도 횟수는 숫자만 입력 가능합니다.");
        return exceptionInfo.makeErrorResponse();
    }

    @ExceptionHandler(HasBlankCarNameException.class)
    public ResponseEntity<ExceptionInfo> hasBlankCarNameHandler() {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(ERROR_PREFIX + "비어있는 자동차 이름이 존재합니다.");
        return exceptionInfo.makeErrorResponse();
    }

}
