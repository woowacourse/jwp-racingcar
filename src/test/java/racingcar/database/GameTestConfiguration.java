package racingcar.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.car.repository.RacingCarDAO;
import racingcar.game.interfaces.GameDAO;
import racingcar.game.repository.RacingCarGameDAO;

@Configuration
public class GameTestConfiguration {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Bean
    public GameDAO gameDAO() {
        final RacingCarDAO racingCarDAO = new RacingCarDAO(this.jdbcTemplate);
        return new RacingCarGameDAO(this.jdbcTemplate, racingCarDAO);
    }
    
}
