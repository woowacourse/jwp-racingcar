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
    private GameDao gameDao;

    private List<RacingCarEntity> racingCarEntities;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        carDao = new CarDao(jdbcTemplate);
        gameDao = new GameDao(jdbcTemplate.getJdbcTemplate());
        jdbcTemplate.getJdbcTemplate().execute("ALTER TABLE game ALTER COLUMN id RESTART WITH 1");
        long id1 = gameDao.save(1);
        long id2 = gameDao.save(2);
        racingCarEntities = List.of(
                new RacingCarEntity("오잉", 1, GameResult.LOSE.getValue(), id1),
                new RacingCarEntity("포이", 1, GameResult.WIN.getValue(), id1),
                new RacingCarEntity("말랑", 1, GameResult.WIN.getValue(), id2));
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
