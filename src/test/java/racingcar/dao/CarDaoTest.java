package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.GameResult;
import racingcar.entity.RacingCarEntity;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@JdbcTest
class CarDaoTest {
    private CarDao carDao;

    private List<RacingCarEntity> racingCarEntities;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        carDao = new CarDao(jdbcTemplate);
        jdbcTemplate.getJdbcTemplate().execute("ALTER TABLE game ALTER COLUMN id RESTART WITH 1");
        racingCarEntities = List.of(
                new RacingCarEntity("오잉", 1, GameResult.LOSE.getValue(), 1),
                new RacingCarEntity("포이", 1, GameResult.WIN.getValue(), 1),
                new RacingCarEntity("말랑", 1, GameResult.WIN.getValue(), 2));
    }

    @Test
    void 탐색_테스트() {
        //given
        carDao.saveAll(racingCarEntities);

        //when
        List<RacingCarEntity> racingCars = carDao.findAll();

        //then
        assertThat(racingCars).hasSize(3);
    }

}
