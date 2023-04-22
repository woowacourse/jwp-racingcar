package racingcar.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import racingcar.db.RacingGameRepository;
import racingcar.domain.Car;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultDto;

import java.util.List;

@TestConfiguration
class TestConfig {

    @Primary
    @Bean
    public RacingGameService RacingGameService() {
        return new RacingGameService(RacingGameRepository());
    }

    @Primary
    @Bean
    public RacingGameRepository RacingGameRepository() {
        return new StubRepository();
    }

    private static class StubRepository implements RacingGameRepository {
        @Override
        public void saveGame(TryCount tryCount, String winners, List<Car> cars) {
        }

        @Override
        public List<GameResultDto> findAllGameResult() {
            return null;
        }

        @Override
        public void deleteAll() {

        }
    }
}
