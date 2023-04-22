package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.RaceResultEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class RaceResultDaoTest {

    RaceResultDao raceResultDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        raceResultDao = new RaceResultDao(jdbcTemplate);
    }

    @Test
    @DisplayName("save() : 경기 결과를 저장할 수 있다.")
    void test_save() throws Exception {
        //given
        final RaceResultEntity raceResultEntity = new RaceResultEntity(4, LocalDateTime.now());

        //when
        final Long savedId = raceResultDao.save(raceResultEntity);

        //then
        assertEquals(1L, savedId);
    }
}
