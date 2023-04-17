package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.domain.RacingGame;
import racingcar.repository.entity.GameEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class InsertRacingGameDaoTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void 저장시_게임_id_가_반환된다() {
        final InsertGameDao insertGameDao = new InsertGameDao(dataSource);
        final GameEntity gameEntity = new GameEntity(null, new RacingGame(List.of("브리"), 5));

        final GameEntity result = insertGameDao.insert(gameEntity);

        assertThat(result.getGameId()).isPositive();
    }
}
