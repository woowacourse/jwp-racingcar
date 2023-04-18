package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import racingcar.repository.CarInfoCollectionRepository;
import racingcar.repository.CarInfoRepository;
import racingcar.repository.RaceRepository;
import racingcar.repository.RaceCollectionRepository;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

@Profile("console")
@Configuration
public class ConsoleAppConfig {
    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    @Bean
    public RaceRepository raceRepository() {
        return new RaceCollectionRepository();
    }

    @Bean
    public CarInfoRepository carInfoRepository() {
        return new CarInfoCollectionRepository();
    }
}
