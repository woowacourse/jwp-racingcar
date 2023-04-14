package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameInputDto;

import java.sql.PreparedStatement;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class RaceDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @BeforeEach
    void setUp() {
        DbInitializer.init(namedParameterJdbcTemplate);
    }
    
    @Test
    void insert() {
        final RaceDao raceDao = new RaceDao(namedParameterJdbcTemplate);
        int raceId = raceDao.insert(new GameInputDto("아벨,스플릿", "12"));
        assertThat(raceId).isOne();
    }
}