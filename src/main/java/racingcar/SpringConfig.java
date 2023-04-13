package racingcar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;

@Configuration
public class SpringConfig {
    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator(0,9);
    }
}
