package racing.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new RacingGameDao(jdbcTemplate);
    }

    @DisplayName("게임을 저장한다.")
    @Test
    void saveGame() {
        // given
        final int count = 10;
        final String query = "select count(*) from game";

        // when
        gameDao.saveGame(count);
        Integer resultCount = jdbcTemplate.queryForObject(query, Integer.class);

        // then
        Assertions.assertThat(resultCount).isEqualTo(1);
    }

}
