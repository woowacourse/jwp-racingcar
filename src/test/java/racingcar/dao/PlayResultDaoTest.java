package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
public class PlayResultDaoTest {
    private PlayResultDao playResultDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        playResultDao = new PlayResultDao(jdbcTemplate);
    }

    @Test
    void insertWithMapAndCount() {
        final RacingGameDto racingGameDto = new RacingGameDto("포비", 10,
                List.of(new CarDto("포비", 10),
                        new CarDto("브라운", 5), new CarDto("구구", 8)));

        playResultDao.insertResult(racingGameDto);

        final String sql = "select count (*) from play_result";
        assertThat(jdbcTemplate.queryForObject(sql, Integer.class)).isEqualTo(1);
    }

    @Test
    void insertWithMapAndFindWinner() {
        final RacingGameDto racingGameDto = new RacingGameDto("토미", 10,
                List.of(new CarDto("토미", 10),
                        new CarDto("브라운", 5), new CarDto("구구", 8)));

        playResultDao.insertResult(racingGameDto);

        final String sql = "select winners from play_result";
        assertThat(jdbcTemplate.queryForObject(sql, String.class)).isEqualTo("토미");
    }
}
