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

public class PlayersHistoryDaoTest {

    @Autowired
    private RacingHistoryDao racingHistoryDao;

    @Autowired
    private PlayersHistoryDao playersHistoryDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE PLAYERS_RESULT IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE PLAYERS_RESULT\n"
                + "(\n"
                + "    id        INT         NOT NULL AUTO_INCREMENT,\n"
                + "    result_id INT         NOT NULL,\n"
                + "    name      VARCHAR(10) NOT NULL,\n"
                + "    position  INT         NOT NULL,\n"
                + "    PRIMARY KEY (id),\n"
                + "    FOREIGN KEY (result_id) REFERENCES PLAY_RESULT (id)\n"
                + ");");
    }

    @Test
    void insertWithMapAndCount() {
        final RacingGameResultDto racingGameResultDto = new RacingGameResultDto("포비", 10,
                List.of(new RacingCarDto("포비", 10),
                        new RacingCarDto("브라운", 5), new RacingCarDto("구구", 8)));

        final int newId = racingHistoryDao.insertResult(racingGameResultDto);
        playersHistoryDao.insertResult(racingGameResultDto.getRacingCars(), newId);

        final String sql = "select count (*) from players_result";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class)).isEqualTo(3);
    }
}
