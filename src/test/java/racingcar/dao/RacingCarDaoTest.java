package racingcar.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.repository.dao.RacingCarDao;
import racingcar.service.dto.RacingCarDto;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@JdbcTest
class RacingCarDaoTest {

    private RacingCarDao racingCarDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    void setup() {
        racingCarDao = new RacingCarDao(jdbcTemplate);
        jdbcTemplate.update("SET REFERENTIAL_INTEGRITY FALSE");
    }

    //TODO: 이 부분도 asertDoesNotThorw에서 끝날 게 아니라 값이 제대로 들어갔는지 확인하는 식으로 하기
    @DisplayName("자동차 이름과 포지션을 저장하는 기능 테스트")
    @Test
    void Should_Success_When_InsertRacingCar() {
        long gameId = 1L;
        final List<RacingCarDto> racingCarDtos = List.of(new RacingCarDto("tori", 2));
        assertDoesNotThrow(() -> racingCarDao.insert(gameId, racingCarDtos));
    }
}
