package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.dto.GameInputDto;

@JdbcTest
class RaceDaoTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    void insert() {
        final RaceDao raceDao = new RaceDao(namedParameterJdbcTemplate);
        int raceId = raceDao.insert(new GameInputDto("아벨,스플릿", "12"));
        assertThat(raceId).isOne();
    }
}