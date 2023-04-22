package racingcar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.strategy.RacingNumberGenerator;
import racingcar.strategy.RacingRandomNumberGenerator;

@Configuration
public class Config {

    @Bean
    public RacingNumberGenerator RacingNumberGenerator() {
        return new RacingRandomNumberGenerator();
    }
}
