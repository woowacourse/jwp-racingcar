package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameJdbcDaoTest {

    @Autowired
    private GameJdbcDao gameJdbcDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.update("ALTER TABLE car ALTER COLUMN id RESTART ");
        jdbcTemplate.update("ALTER TABLE game ALTER COLUMN id RESTART ");
    }

    @Test
    @DisplayName("게임을 저장한다.")
    void insert() {
        int gameId = gameJdbcDao.insertGame(5);
        assertThat(gameId).isEqualTo(1);
    }
}
