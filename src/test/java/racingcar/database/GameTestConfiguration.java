package racingcar.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.car.repository.RacingCarDAO;
import racingcar.game.interfaces.GameDAO;
import racingcar.game.repository.RacingCarGameDAO;

@Configurable
public class GameTestConfiguration {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Bean
    public GameDAO racingCarGameDAO() {
        final RacingCarDAO racingCarDAO = new RacingCarDAO(this.jdbcTemplate);
        return new RacingCarGameDAO(this.jdbcTemplate, racingCarDAO);
    }
    
}
