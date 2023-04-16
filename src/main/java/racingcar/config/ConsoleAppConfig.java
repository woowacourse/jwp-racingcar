package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import racingcar.ConsoleRacingCarApplication;
import racingcar.controller.RacingController;
import racingcar.dao.CarInfoCollectionRepository;
import racingcar.dao.CarInfoRepository;
import racingcar.dao.RaceRepository;
import racingcar.dao.RacingCollectionRepository;
import racingcar.service.RacingService;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

@Profile("console")
@Configuration
public class ConsoleAppConfig {
    @Bean
    public ConsoleRacingCarApplication consoleRacingCarApplication() {
        return new ConsoleRacingCarApplication(racingController());
    }

    @Bean
    public RacingController racingController() {
        return new RacingController(consoleRacingService());
    }

    @Bean
    public RacingService consoleRacingService() {
        return new RacingService(numberGenerator(), raceRepository(), carInfoRepository());
    }

    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    @Bean
    public RaceRepository raceRepository() {
        return new RacingCollectionRepository();
    }

    @Bean
    public CarInfoRepository carInfoRepository() {
        return new CarInfoCollectionRepository();
    }
}
