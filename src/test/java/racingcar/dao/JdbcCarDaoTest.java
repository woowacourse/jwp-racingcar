package racingcar.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;
    private CarDao carDao;

    @BeforeEach
    void setUp() {
        gameDao = new JdbcGameDao(jdbcTemplate);
        carDao = new JdbcCarDao(jdbcTemplate);
    }

    @Test
    void 게임_id와_함께_차량_정보를_저장한다() {
        // given
        final long gameId = gameDao.insert(5);

        // when
        final int affectedRows = carDao.insert("huchu", 3, gameId, true);

        // then
        assertThat(affectedRows).isEqualTo(1);
    }

    @Test
    void 데이터_행의_개수를_센다() {
        //given
        carDao.insert("huchu", 1, 1L, true);

        //when
        final int rowCount = carDao.countRows();

        //then
        assertThat(rowCount).isEqualTo(1);
    }

    @Test
    void 모든_데이터를_삭제한다() {
        //given
        carDao.insert("huchu", 1, 1L, true);

        //when
        carDao.deleteAll();
        final int rowCount = carDao.countRows();

        //then
        assertThat(rowCount).isEqualTo(0);
    }

    @AfterEach
    void tearDown() {
        carDao.deleteAll();
    }
}
