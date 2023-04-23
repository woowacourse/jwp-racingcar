package racingcar.global.exception.response;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExceptionTargetInfoResponse {

    private static final String DEFAULT_ERROR_MESSAGE = "";

    private final String field;
    private final String value;
    private final String reason;

    private ExceptionTargetInfoResponse(final String field, final String value, final String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }

    public static List<ExceptionTargetInfoResponse> from(BindingResult bindingResult) {

        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return fieldErrors.stream()
                          .map(error -> new ExceptionTargetInfoResponse(
                                  error.getField(),
                                  Objects.requireNonNullElse(error.getRejectedValue(), DEFAULT_ERROR_MESSAGE)
                                         .toString(),
                                  Objects.requireNonNullElse(error.getDefaultMessage(), DEFAULT_ERROR_MESSAGE))
                          )
                          .collect(Collectors.toList());
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }
}
