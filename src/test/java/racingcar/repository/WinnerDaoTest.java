package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.WinnerDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.RacingGame;
import racingcar.repositoryImpl.RacingGameMapper;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class WinnerDaoTest {

    private WinnerDao winnerDao;
    private int gameId;
    private int carId;

    @Autowired
    void setUp(final JdbcTemplate jdbcTemplate) {
        final GameEntity gameEntity = RacingGameMapper.toGameEntity(new RacingGame(List.of("브리"), 5));

        gameId = RepositoryFactory.gamesDao(jdbcTemplate).save(gameEntity).getGameId();
        carId = RepositoryFactory.carDao(jdbcTemplate)
                .insertAll(List.of(new CarEntity("브리", 9, gameId)))
                .get(0)
                .getCarId();
        winnerDao = RepositoryFactory.winnerDao(jdbcTemplate);
    }

    @Test
    void 우승자_저장() {
        final List<WinnerEntity> result = winnerDao.insertAll(List.of(new WinnerEntity(gameId, carId)));

        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0).getGameId()).isPositive()
        );
    }
}
