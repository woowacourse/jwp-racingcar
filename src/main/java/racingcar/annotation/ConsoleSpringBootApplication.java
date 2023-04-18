package racingcar.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import racingcar.RacingCarWebApplication;
import racingcar.controller.WebController;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {RacingCarWebApplication.class, WebController.class}))
public @interface ConsoleSpringBootApplication {
}
