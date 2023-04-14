package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameResultDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class PlayersHistoryDaoTest {

    @Autowired
    private RacingHistoryDao racingHistoryDao;
    @Autowired
    private PlayersHistoryDao playersHistoryDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
