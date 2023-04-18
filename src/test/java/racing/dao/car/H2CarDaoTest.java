package racing.dao.car;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racing.dao.game.H2GameDao;
import racing.domain.Car;
import racing.domain.Game;
import racing.dto.CarDto;

@Sql("/resetTable.sql")
@JdbcTest
class H2CarDaoTest {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    void insert() {
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        h2GameDao.insert(new Game("5"));
        final CarDao carDao = new H2CarDao(namedParameterJdbcTemplate);
        carDao.insert(new Car("스플릿"), 1);
        final List<CarDto> carsInGame = carDao.findByGameId(1);
        Assertions.assertThat(carsInGame).hasSize(1);
        Assertions.assertThat(carsInGame.get(0).getName()).isEqualTo("스플릿");
    }

    @Test
    void findByGameId() {
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        h2GameDao.insert(new Game("5"));
        h2GameDao.insert(new Game("7"));
        final CarDao carDao = new H2CarDao(namedParameterJdbcTemplate);
        carDao.insert(new Car("게임1"), 1);
        carDao.insert(new Car("게임2"), 2);
        final List<CarDto> carsInGame = carDao.findByGameId(2);
        Assertions.assertThat(carsInGame).hasSize(1);
        Assertions.assertThat(carsInGame.get(0).getName()).isEqualTo("게임2");
    }
}
