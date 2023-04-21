package racingcar.config;

import org.springframework.context.annotation.Bean;
import racingcar.domain.NumberPicker;
import racingcar.domain.RandomNumberPicker;

public class WebDependencyConfig {

    @Bean
    public NumberPicker injectNumberPicker() {
        return RandomNumberPicker.getInstance();
    }
}
