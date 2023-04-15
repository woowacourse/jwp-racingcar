package racingcar.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import racingcar.dao.GameResultDao;
import racingcar.dao.ResultCarDao;

@TestConfiguration
class TestConfig {

    @Primary
    @Bean
    public RacingGameService RacingGameService() {
        return new RacingGameService(GameResultDto(), ResultCarDao());
    }

    @Primary
    @Bean
    public GameResultDao GameResultDto() {
        return new StubGameResultDao();
    }

    @Primary
    @Bean
    public ResultCarDao ResultCarDao() {
        return new StubResultCarDao();
    }

}
