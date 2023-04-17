package racingcar.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;

@Configuration
public class RacingWebConfig {

    @Bean
    public MovingStrategy movingStrategy() {
        return new RandomMovingStrategy();
    }
}
