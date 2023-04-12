package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class InsertGameDaoTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void 저장시_게임_id_가_반환된다() {
        final InsertGameDao insertGameDao = new InsertGameDao(dataSource);

        final int result = insertGameDao.save(10);

        assertThat(result).isPositive();
    }
}
