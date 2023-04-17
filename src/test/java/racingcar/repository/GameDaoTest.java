package racingcar.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameDaoTest {
    @Autowired
    private GameDao gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE;");
        jdbcTemplate.execute("TRUNCATE TABLE record RESTART IDENTITY;");
        jdbcTemplate.execute("TRUNCATE TABLE game RESTART IDENTITY;");
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE;");

        gameDao.insert(10);
        gameDao.insert(20);
    }

    @Test
    @DisplayName("값을 넣었을때 자동으로 증가되는 id 값을 반환하는 테스트")
    void insert() {
        long id = gameDao.insert(10);
        Assertions.assertThat(id).isEqualTo(3);
    }
}
