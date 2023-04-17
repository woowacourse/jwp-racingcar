package racingcar.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import racingcar.strategy.MovingStrategy;

@TestConfiguration
public class TestConfig {
    @Bean
    MovingStrategy movingStrategy() {
        return new FixedMovingStrategy();
    }
}
