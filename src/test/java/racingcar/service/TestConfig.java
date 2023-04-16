package racingcar.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import racingcar.dao.RacingGameRepository;
import racingcar.dto.GameResultDto;
import racingcar.dto.response.GameResponseDto;

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
        public void saveGame(GameResultDto resultDto) {
        }

        @Override
        public List<GameResponseDto> findAllGame() {
            return null;
        }

        @Override
        public void deleteAll() {

        }
    }
}
