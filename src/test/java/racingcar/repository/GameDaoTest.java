package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

@TestPropertySource(locations = "/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class GameDaoTest {

    @Autowired private GameDao gameDao;
    @Autowired private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS record;\n" +
                "DROP TABLE IF EXISTS game;\n" +
                "\n" +
                "CREATE TABLE game\n" +
                "(\n" +
                "    id          int PRIMARY KEY AUTO_INCREMENT      NOT NULL,\n" +
                "    trial_count int                                 NOT NULL,\n" +
                "    game_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE record\n" +
                "(\n" +
                "    game_id     int         NOT NULL,\n" +
                "    position    int         NOT NULL,\n" +
                "    is_winner   boolean     NOT NULL,\n" +
                "    player_name varchar(30) NOT NULL,\n" +
                "    PRIMARY KEY (game_id, player_name),\n" +
                "    FOREIGN KEY (game_id) REFERENCES game (id)\n" +
                ");\n");
        initGameTable();
        initGameTableData();
    }

    private void initGameTable() {
        jdbcTemplate.execute("DROP TABLE record IF EXISTS");
        jdbcTemplate.execute("DROP TABLE game IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE game\n" +
                "(\n" +
                "    id          int PRIMARY KEY AUTO_INCREMENT      NOT NULL,\n" +
                "    trial_count int                                 NOT NULL,\n" +
                "    game_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL\n" +
                ");");
    }

    private void initGameTableData() {
        List<Object[]> trial_count = Arrays.asList(
                new String[]{"10"},
                new String[]{"20"}
        );
        jdbcTemplate.batchUpdate("INSERT INTO game(trial_count) VALUES (?)", trial_count);
    }

    @Test
    @DisplayName("값을 넣었을때 자동으로 증가되는 id 값을 반환하는 테스트")
    void 값을_넣었을때_자동으로_증가되는_id_값을_반환하는_테스트() {
        assertThat(gameDao.countAll()).isEqualTo(2);

        long id = gameDao.insert(10);

        assertThat(id).isEqualTo(3L);
    }

    @Test
    @DisplayName("테이블의 모든 행의 수를 반환한다")
    void 테이블의_모든_행의_수를_반환한다() {
        Integer result = gameDao.countAll();
        assertThat(result).isEqualTo(2);

        gameDao.insert(10);
        assertThat(gameDao.countAll()).isEqualTo(3);

        jdbcTemplate.execute("DELETE FROM game");
        assertThat(gameDao.countAll()).isEqualTo(0);
    }
}
