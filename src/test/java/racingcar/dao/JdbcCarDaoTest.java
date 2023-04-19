package racingcar.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        carDao = new JdbcCarDao(jdbcTemplate);

        final GameDao gameDao = new JdbcGameDao(jdbcTemplate);
        gameId = gameDao.insert(2);
    }

    @Test
    void 게임_id와_함께_차량_정보를_저장한다() {
        // given
        // when
        final int affectedRows = carDao.insert("huchu", 1, gameId, true);

        // then
        assertThat(affectedRows).isEqualTo(1);
    }

    @Test
    void 데이터_행의_개수를_센다() {
        //given
        carDao.insert("huchu", 1, gameId, true);

        //when
        final int rowCount = carDao.countRows();

        //then
        assertThat(rowCount).isEqualTo(1);
    }

    @Test
    void 모든_데이터를_삭제한다() {
        //given
        carDao.insert("huchu", 1, gameId, true);

        //when
        carDao.deleteAll();
        final int rowCount = carDao.countRows();

        //then
        assertThat(rowCount).isEqualTo(0);
    }

    @Test
    void 우승자_이름을_찾는다() {
        //given
        carDao.insert("huchu", 0, gameId, false);
        carDao.insert("gavi", 1, gameId, true);
        carDao.insert("kyle", 1, gameId, true);

        //when
        final List<CarNameDTO> carNameDTOS = carDao.findWinners(gameId);

        //then
        assertThat(carNameDTOS).hasSize(2).containsExactly(new CarNameDTO("gavi"), new CarNameDTO("kyle"));
    }

    @Test
    void 모든_자동차의_이름과_위치_정보를_찾는다() {
        //given
        carDao.insert("huchu", 0, gameId, false);
        carDao.insert("gavi", 1, gameId, true);
        carDao.insert("kyle", 1, gameId, true);

        //when
        final List<CarNamePositionDTO> allCars = carDao.findAllCarNamesAndPositions(gameId);

        //then
        assertThat(allCars).hasSize(3).containsExactly(new CarNamePositionDTO("huchu", 0), new CarNamePositionDTO("gavi", 1), new CarNamePositionDTO("kyle", 1));
    }

    @AfterEach
    void tearDown() {
        carDao.deleteAll();
    }
}
