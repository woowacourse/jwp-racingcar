package racingcar;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import racingcar.controller.RacingGameController;
import racingcar.dao.GameDao;
import racingcar.dao.PlayerDao;
import racingcar.dao.WinnerDao;
import racingcar.repository.GameRepository;
import racingcar.repository.JdbcTemplateGameRepository;
import racingcar.service.GameService;

@Configuration
public class SpringConfig {

    @Bean
    public GameService gameService() {
        return new GameService(gameRepository());
    }

    @Bean
    public RacingGameController racingGameController() {
        return new RacingGameController(gameService());
    }

    @Bean
    public GameRepository gameRepository() {
        return new JdbcTemplateGameRepository(gameDao(), playerDao(), winnerDao());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        final DataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:testdb;MODE=MySQL", "sa", "");
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public GameDao gameDao() {
        return new GameDao(jdbcTemplate());
    }

    @Bean
    public PlayerDao playerDao() {
        return new PlayerDao(jdbcTemplate());
    }

    @Bean
    public WinnerDao winnerDao() {
        return new WinnerDao(jdbcTemplate());
    }
}
