package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racingcar.dto.GameInputDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql("/data.sql")
@JdbcTest
class RaceDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RaceDao raceDao;
    
    @BeforeEach
    void setUp() {
        raceDao = new RaceDao(namedParameterJdbcTemplate);
    }
    
    @Test
    void race의_정보를_저장하면_해당_정보의_raceId를_반환한다() {
        long raceId = raceDao.insert(new GameInputDto("아벨,스플릿", "12"));
        assertThat(raceId).isOne();
    }
    
    @Test
    void 모든_race의_id를_반환한다() {
        raceDao.insert(new GameInputDto("아벨1,스플릿1", "12"));
        raceDao.insert(new GameInputDto("아벨2,스플릿2", "20"));
        raceDao.insert(new GameInputDto("아벨3,스플릿3", "30"));
        List<Long> ids = raceDao.findAllIds();
        assertThat(ids).containsExactly(1L, 2L, 3L);
    }
}