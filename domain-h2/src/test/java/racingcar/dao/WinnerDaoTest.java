package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.RacingGame;
import racingcar.repositoryImpl.RacingGameMapper;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class WinnerDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private WinnerDao winnerDao;
    private int gameId;
    private int carId;

    @BeforeEach
    void setUp() {
        final GameEntity gameEntity = RacingGameMapper.toGameEntity(new RacingGame(List.of("브리"), 5));

        gameId = RepositoryFactory.gamesDao(jdbcTemplate).insert(gameEntity).getGameId().getValue();
        carId = RepositoryFactory.carDao(jdbcTemplate)
                .insertAll(List.of(new CarEntity("브리", 9, gameId)))
                .get(0)
                .getCarId()
                .getValue();
        winnerDao = RepositoryFactory.winnerDao(jdbcTemplate);
    }

    @Test
    void 우승자_저장() {
        final List<WinnerEntity> result = winnerDao.insertAll(List.of(new WinnerEntity(gameId, carId)));

        assertAll(
                () -> Assertions.assertThat(result).hasSize(1),
                () -> assertThat(result.get(0).getGameId().getValue()).isPositive()
        );
    }
}
