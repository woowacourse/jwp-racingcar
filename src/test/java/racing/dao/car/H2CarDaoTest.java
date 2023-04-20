package racing.dao.car;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("DB에 name과 gameId(fk) 를 이용하여 데이터를 삽압한다.")
    @Test
    void insert() {
        //given
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        final CarDao carDao = new H2CarDao(namedParameterJdbcTemplate);
        final int gameId = h2GameDao.insert(new Game("5"));

        //when
        carDao.insert(new Car("스플릿"), gameId);

        //then
        final List<CarDto> carsInGame = carDao.findByGameId(1);
        Assertions.assertThat(carsInGame).hasSize(1);
        Assertions.assertThat(carsInGame.get(0).getName()).isEqualTo("스플릿");
    }

    @DisplayName("해당 게임의 아이디를 가지고 있는 모든 데이터를 조회한다.")
    @Test
    void findByGameId() {
        //given
        final H2GameDao h2GameDao = new H2GameDao(namedParameterJdbcTemplate);
        final CarDao carDao = new H2CarDao(namedParameterJdbcTemplate);
        final int firstGameId = h2GameDao.insert(new Game("5"));
        final int secondGameId = h2GameDao.insert(new Game("7"));
        carDao.insert(new Car("게임1-1"), 1);
        carDao.insert(new Car("게임1-2"), 1);
        carDao.insert(new Car("게임2"), 2);

        //when
        final List<CarDto> carsInFirstGame = carDao.findByGameId(firstGameId);
        final List<CarDto> carsInSecondGame = carDao.findByGameId(secondGameId);

        //then
        Assertions.assertThat(carsInFirstGame).hasSize(2);
        Assertions.assertThat(carsInSecondGame).hasSize(1);
    }
}
