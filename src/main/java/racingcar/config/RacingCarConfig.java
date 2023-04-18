package racingcar.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;

@Configurable
public class RacingCarConfig {

    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }
}
