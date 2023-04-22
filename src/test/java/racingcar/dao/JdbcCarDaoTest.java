package racingcar.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.dto.CarDTO;
import racingcar.dao.dto.CarNameDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private GameDao gameDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        carDao = new JdbcCarDao(jdbcTemplate);
        gameDao = new JdbcGameDao(jdbcTemplate);
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
        final List<CarNameDTO> carNameDTOs = carDao.findWinners(gameId);

        //then
        assertSoftly(softly -> {
            softly.assertThat(carNameDTOs).hasSize(2);
            softly.assertThat(carNameDTOs.get(0).getName()).isEqualTo("gavi");
            softly.assertThat(carNameDTOs.get(1).getName()).isEqualTo("kyle");
        });
    }

    @Test
    void 모든_자동차의_이름과_위치_정보를_찾는다() {
        //given
        carDao.insert("huchu", 0, gameId, false);
        carDao.insert("gavi", 1, gameId, true);
        carDao.insert("kyle", 1, gameId, true);

        //when
        final List<CarDTO> allCars = carDao.findAllCarNamesAndPositions(gameId);

        //then
        assertSoftly(softly -> {
            softly.assertThat(allCars).hasSize(3);
            softly.assertThat(getName(allCars, 0)).isEqualTo("huchu");
            softly.assertThat(getName(allCars, 1)).isEqualTo("gavi");
            softly.assertThat(getName(allCars, 2)).isEqualTo("kyle");
            softly.assertThat(getPosition(allCars, 0)).isEqualTo(0);
            softly.assertThat(getPosition(allCars, 1)).isEqualTo(1);
            softly.assertThat(getPosition(allCars, 2)).isEqualTo(1);
        });
    }

    private String getName(final List<CarDTO> allCars, final int index) {
        return allCars.get(index).getName();
    }

    private int getPosition(final List<CarDTO> allCars, final int index) {
        return allCars.get(index).getPosition();
    }

    @AfterEach
    void tearDown() {
        carDao.deleteAll();
        gameDao.deleteAll();
    }
}
