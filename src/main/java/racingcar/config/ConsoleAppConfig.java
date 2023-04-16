package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import racingcar.ConsoleRacingCarApplication;
import racingcar.controller.RacingController;
import racingcar.repository.CarInfoCollectionRepository;
import racingcar.repository.CarInfoRepository;
import racingcar.repository.RaceRepository;
import racingcar.repository.RacingCollectionRepository;
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
