package racingcar.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import racingcar.WebRacingCarApplication;
import racingcar.controller.WebRacingCarController;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {WebRacingCarApplication.class, WebRacingCarController.class}
))
public @interface ConsoleSpringBootApplication {

}
