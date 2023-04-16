package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import racingcar.repository.CarInfoH2Repository;
import racingcar.repository.CarInfoRepository;
import racingcar.repository.RaceRepository;
import racingcar.repository.RacingH2Repository;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

import javax.sql.DataSource;

@Profile("web")
@Configuration
public class WebAppConfig {
    private final DataSource dataSource;

    public WebAppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    @Bean
    public RaceRepository raceRepository() {
        return new RacingH2Repository(dataSource);
    }

    @Bean
    public CarInfoRepository carInfoRepository() {
        return new CarInfoH2Repository(dataSource);
    }
}
