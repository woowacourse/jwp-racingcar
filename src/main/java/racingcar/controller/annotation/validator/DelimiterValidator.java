package racingcar.controller.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import racingcar.controller.annotation.ContainDelimiter;

public class DelimiterValidator implements ConstraintValidator<ContainDelimiter, String> {

    private String delimiter;

    @Override
    public void initialize(final ContainDelimiter constraintAnnotation) {
        delimiter = constraintAnnotation.getDelimiter();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && value.contains(delimiter);
    }
}
