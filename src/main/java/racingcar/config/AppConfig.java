package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.utils.RacingNumberGenerator;
import racingcar.utils.RacingRandomNumberGenerator;

@Configuration
public class AppConfig {

    @Bean
    public RacingNumberGenerator racingNumberGenerator() {
        return new RacingRandomNumberGenerator();
    }
}
