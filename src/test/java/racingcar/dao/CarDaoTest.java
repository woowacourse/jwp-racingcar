package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.GameResult;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarResultDto;

@JdbcTest
class CarDaoTest {
    private CarDao carDao;
    private GameDao gameDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        carDao = new CarDao(jdbcTemplate);
        gameDao = new GameDao(jdbcTemplate.getJdbcTemplate().getDataSource());
        gameDao.save(1);
        gameDao.save(2);
    }

    @Test
    void 자동차_저장_테스트() {
        //given
        RacingCar racingCar1 = new RacingCar("오잉");
        RacingCar racingCar2 = new RacingCar("포이");
        RacingCar racingCar3 = new RacingCar("말랑");
        List<RacingCarResultDto> cars = List.of(
                RacingCarResultDto.of(racingCar1, GameResult.WIN.getValue(), 1),
                RacingCarResultDto.of(racingCar2, GameResult.WIN.getValue(), 1),
                RacingCarResultDto.of(racingCar3, GameResult.WIN.getValue(), 2));

        //when
        carDao.saveAll(cars);
        List<RacingCarDto> queriedCars = carDao.findCarsById(1);

        //then
        assertThat(queriedCars).hasSize(2);
    }
}
