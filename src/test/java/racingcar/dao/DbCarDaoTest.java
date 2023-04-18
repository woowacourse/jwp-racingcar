package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.GameResult;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@JdbcTest
class DbCarDaoTest {
    private DbCarDao dbCarDao;
    private DbGameDao dbGameDao;

    private List<RacingCarResultDto> cars;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        dbCarDao = new DbCarDao(jdbcTemplate);
        dbGameDao = new DbGameDao(jdbcTemplate.getJdbcTemplate().getDataSource());
        jdbcTemplate.getJdbcTemplate().execute("ALTER TABLE game ALTER COLUMN id RESTART WITH 1");
        long id1 = dbGameDao.save(1);
        long id2 = dbGameDao.save(2);
        cars = List.of(
                RacingCarResultDto.of(new RacingCar("오잉"), GameResult.LOSE.getValue(), id1),
                RacingCarResultDto.of(new RacingCar("포이"), GameResult.WIN.getValue(), id1),
                RacingCarResultDto.of(new RacingCar("말랑"), GameResult.WIN.getValue(), id2));
    }

    @Test
    void 자동차_저장_테스트() {
        //given
        //when
        dbCarDao.saveAll(cars);
        List<RacingCarDto> queriedCars = dbCarDao.findCarsById(1);

        //then
        assertThat(queriedCars).hasSize(2)
                .extracting("name", "position")
                .containsExactly(
                        tuple("오잉", 1),
                        tuple("포이", 1)
                );
    }
}
