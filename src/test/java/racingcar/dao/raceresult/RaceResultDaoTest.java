package racingcar.dao.raceresult;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
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
        final RaceResultEntity raceResultEntity = new RaceResultEntity(4, "a,b,c", LocalDateTime.now());

        //when
        final int savedId = raceResultDao.save(raceResultEntity);

        //then
        assertEquals(savedId, 1);
    }

    @Test
    @DisplayName("findAllRaceResult() : 전체 경기 결과를 조회할 수 있다.")
    void test_findAllRaceResult() throws Exception {
        //given
        final String winner1 = "빙봉";
        final String winner2 = "a,b,c,d";

        //when
        final Map<String, List<CarEntity>> allRaceResult = raceResultDao.findAllRaceResult();

        //then
        assertAll(
                () -> Assertions.assertThat(allRaceResult).hasSize(2),
                () -> Assertions.assertThat(allRaceResult).containsOnlyKeys(winner1, winner2)
        );
    }
}
