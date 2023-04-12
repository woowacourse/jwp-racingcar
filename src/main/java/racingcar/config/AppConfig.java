package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.strategy.RacingNumberGenerator;
import racingcar.strategy.RacingRandomNumberGenerator;

@Configuration
public class AppConfig {

    @Bean
    public RacingNumberGenerator racingNumberGenerator() {
        return new RacingRandomNumberGenerator();
    }
}
