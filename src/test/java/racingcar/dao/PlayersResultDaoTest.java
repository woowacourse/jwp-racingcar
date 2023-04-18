package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.PlayerResultDto;
import racingcar.dto.RacingGameDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PlayersResultDaoTest {
    @Autowired
    private PlayResultDao playResultDao;

    @Autowired
    private PlayersResultDao playersResultDao;

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
        final RacingGameDto racingGameDto = new RacingGameDto("포비", 10,
                List.of(new PlayerResultDto("포비", 10),
                        new PlayerResultDto("브라운", 5), new PlayerResultDto("구구", 8)));

        final int newId = playResultDao.insertResult(racingGameDto);
        playersResultDao.insertResult(racingGameDto.getRacingCars(), newId);

        final String sql = "select count (*) from players_result";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class)).isEqualTo(3);
    }
}
