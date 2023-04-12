package racingcar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.dao.PlayResultDao;
import racingcar.dao.PlayerDao;
import racingcar.service.CarService;
import racingcar.utils.RacingRandomNumberGenerator;

@Configuration
public class AppConfig {

    @Bean
    public CarService carService(PlayerDao playerDao, PlayResultDao playResultDao) {
        return new CarService(new RacingRandomNumberGenerator(), playerDao, playResultDao);
    }
}
