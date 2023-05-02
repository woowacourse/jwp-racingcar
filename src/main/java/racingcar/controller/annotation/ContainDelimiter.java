package racingcar.controller.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import racingcar.controller.annotation.validator.DelimiterValidator;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DelimiterValidator.class)
public @interface ContainDelimiter {
    String message() default "유효하지 않은 자동차 이름 입력";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String getDelimiter() default ",";
}
