package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.domain.NumberPicker;
import racingcar.domain.RandomNumberPicker;

@Configuration
public class WebDependencyConfig {

    @Bean
    public NumberPicker injectNumberPicker() {
        return RandomNumberPicker.getInstance();
    }
}
