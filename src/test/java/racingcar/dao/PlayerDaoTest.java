package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.GameInputDto;
import racingcar.dto.RaceResultDto;
import racingcar.game.Game;

@JdbcTest
class PlayerDaoTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private PlayerDao playerDao;
    private RaceDao raceDao;

    @BeforeEach
    void setUp() {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        jdbcTemplate.execute("DROP TABLE RACE CASCADE");
        jdbcTemplate.execute("DROP TABLE PLAYER CASCADE");
        jdbcTemplate.execute("CREATE TABLE RACE (id INT  NOT NULL AUTO_INCREMENT,"
            + "play_count  INT NOT NULL,"
            + "created_at  DATETIME  NOT NULL default current_timestamp,"
            + "PRIMARY KEY (id))");
        jdbcTemplate.execute("CREATE TABLE PLAYER ("
            + "    id          INT           NOT NULL AUTO_INCREMENT,"
            + "    name        VARCHAR(50)   NOT NULL,"
            + "    identifier  INT           NOT NULL,"
            + "    position    INT           NOT NULL,"
            + "    race_id     INT           NOT NULL,"
            + "    PRIMARY KEY (id),"
            + "    FOREIGN KEY (race_id) REFERENCES RACE(id));");
        playerDao = new PlayerDao(namedParameterJdbcTemplate);
        raceDao = new RaceDao(namedParameterJdbcTemplate);
    }

    @Test
    void getWinnerCarIds() {
        Game game = new Game("아벨,스플릿,포비", "12");
        raceDao.insert(new GameInputDto("아벨,스플릿,포비", "12"));
        playerDao.insertAll(new RaceResultDto(game), 1);

        List<Integer> winnerCarIds = playerDao.getWinnerCarIds(1, new RaceResultDto(game));
        assertThat(winnerCarIds).containsExactly(1, 2, 3);
    }

    @Test
    void getWinnerCardId() {
        Game game = new Game("아벨,스플릿", "2");
        raceDao.insert(new GameInputDto("아벨,스플릿", "2"));
        playerDao.insertAll(new RaceResultDto(game), 1);

        int carId = playerDao.getWinnerCarId(1, new CarDto(new Car("스플릿", 0)));
        assertThat(carId).isEqualTo(2);
    }
}