package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameResultDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RacingHistoryDaoTest {
    @Autowired
    private RacingHistoryDao racingHistoryDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE PLAYERS_RESULT IF EXISTS");
        jdbcTemplate.execute("DROP TABLE PLAY_RESULT IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE PLAY_RESULT\n"
                + "(\n"
                + "    id         INT         NOT NULL AUTO_INCREMENT,\n"
                + "    winners    VARCHAR(50) NOT NULL,\n"
                + "    play_count INT         NOT NULL,\n"
                + "    created_at DATETIME    NOT NULL default current_timestamp,\n"
                + "    PRIMARY KEY (id)\n"
                + ");");
    }

    @Test
    void insertWithMapAndCount() {
        final RacingGameResultDto racingGameResultDto = new RacingGameResultDto("포비", 10,
                List.of(new RacingCarDto("포비", 10),
                        new RacingCarDto("브라운", 5), new RacingCarDto("구구", 8)));

        racingHistoryDao.insertResult(racingGameResultDto);

        final String sql = "select count (*) from play_result";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class)).isEqualTo(1);
    }

    @Test
    void insertWithMapAndFindWinner() {
        final RacingGameResultDto racingGameResultDto = new RacingGameResultDto("토미", 10,
                List.of(new RacingCarDto("토미", 10),
                        new RacingCarDto("브라운", 5), new RacingCarDto("구구", 8)));

        racingHistoryDao.insertResult(racingGameResultDto);

        final String sql = "select winners from play_result";
        assertThat(jdbcTemplate.queryForObject(sql, String.class)).isEqualTo("토미");
    }
}
