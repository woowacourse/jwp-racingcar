import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import racingcar.dao.RacingGameDao;

@TestConfiguration
public class TestConfig {

    @Primary
    @Bean
    public RacingGameDao RacingGameDao() {
        return new StubRacingGameDao();
    }
}