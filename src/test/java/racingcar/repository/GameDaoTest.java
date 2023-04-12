package racingcar.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

@TestPropertySource(locations = "/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameDaoTest {
    @Autowired
    private GameDao gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE game IF EXISTS");

        jdbcTemplate.execute("CREATE TABLE game (\n" +
                "    id int PRIMARY KEY AUTO_INCREMENT,\n" +
                "    trial_count int NOT NULL,\n" +
                "    game_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL\n" +
                ");");

        List<Object[]> trial_count = Arrays.asList(new String[]{"10"}, new String[]{"20"});
        jdbcTemplate.batchUpdate("INSERT INTO game(trial_count) VALUES (?)", trial_count);
    }

    @Test
    @DisplayName("값을 넣었을때 자동으로 증가되는 id 값을 반환하는 테스트")
    void insert() {
        long id = gameDao.insert(10);
        Assertions.assertThat(id).isEqualTo(3);
    }
}
