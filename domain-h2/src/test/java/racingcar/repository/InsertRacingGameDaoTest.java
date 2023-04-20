package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.InsertGameDao;
import racingcar.dao.entity.GameEntity;
import racingcar.domain.RacingGame;
import racingcar.repositoryImpl.RacingGameMapper;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class InsertRacingGameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 저장시_게임_id_가_반환된다() {
        final InsertGameDao insertGameDao = new InsertGameDao(jdbcTemplate);
        final GameEntity gameEntity = RacingGameMapper.toGameEntity(new RacingGame(List.of("브리"), 5));

        final GameEntity result = insertGameDao.insert(gameEntity);

        assertThat(result.getGameId()).isPositive();
    }
}
