package racingcar;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racingcar.repository.GameRepository;
import racingcar.repository.JdbcTemplateGameRepository;
import racingcar.service.GameService;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public GameService gameService() {
        return new GameService(gameRepository());
    }

    @Bean
    public GameRepository gameRepository() {
        return new JdbcTemplateGameRepository(dataSource);
    }
}
