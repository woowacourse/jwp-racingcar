package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.RacingGameEntity;

@JdbcTest
class CarJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CarDao carDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        carDao = new CarJdbcDao(jdbcTemplate);
        RacingGameDao racingGameDao = new RacingGameJdbcDao(jdbcTemplate);
        gameId = racingGameDao.save(new RacingGameEntity(10));

        CarEntity boxster = new CarEntity("boxster", 10, true, gameId);
        CarEntity encho = new CarEntity("encho", 7, false, gameId);

        jdbcTemplate.update("INSERT INTO CAR (name, position, winner, racing_game_id) VALUES (?, ?, ?, ?)",
                boxster.getName(), boxster.getPosition(), boxster.isWinner(), boxster.getRacingGameId());
        jdbcTemplate.update("INSERT INTO CAR (name, position, winner, racing_game_id) VALUES (?, ?, ?, ?)",
                encho.getName(), encho.getPosition(), encho.isWinner(), encho.getRacingGameId());

    }

    @Test
    @DisplayName("car를 여러 개 저장한다")
    void insert() {
        List<CarEntity> carDtos = List.of(new CarEntity("gumak", 9, false, gameId),
                new CarEntity("benz", 10, true, gameId));

        carDao.saveAll(carDtos);

        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM CAR", Integer.class);

        assertThat(count).isEqualTo(4);
    }

    @Test
    @DisplayName("레이싱 게임 id로 car를 조회한다")
    void findByGameId() {
        List<CarEntity> carEntities = carDao.findByRacingGameId(gameId);

        assertThat(carEntities).hasSize(2);
    }
}
