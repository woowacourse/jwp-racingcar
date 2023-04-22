package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.RaceResultDao;
import racingcar.entity.CarEntity;
import racingcar.entity.RaceResultEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
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

    @Test
    @DisplayName("findAllRaceResult() : 전체 경기 결과를 조회할 수 있다.")
    void test_findAllRaceResult() throws Exception {
        //given
        final List<Long> ids = List.of(3L, 4L);

        //when
        final List<RaceResultEntity> raceResultEntities =  raceResultDao.findAllRaceResult();

        //then
        assertAll(
                () -> assertEquals(2, raceResultEntities.size()),
                () -> Assertions.assertThat(raceResultEntities).extracting("id")
                                .containsExactlyElementsOf(ids)
        );
    }
}
