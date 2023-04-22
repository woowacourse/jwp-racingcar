package racingcar.dao;

import org.junit.jupiter.api.*;
import racingcar.dto.CarNameDTO;
import racingcar.dto.CarNamePositionDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class LocalCarDaoTest {

    private final CarDao carDao = new LocalCarDao();
    private final GameDao gameDao = new LocalGameDao();

    private Long gameId;

    @BeforeEach
    void setUp() {
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
        final List<CarNamePositionDTO> allCars = carDao.findAllCarNamesAndPositions(gameId);

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

    private String getName(final List<CarNamePositionDTO> allCars, final int index) {
        return allCars.get(index).getName();
    }

    private int getPosition(final List<CarNamePositionDTO> allCars, final int index) {
        return allCars.get(index).getPosition();
    }

    @AfterEach
    void tearDown() {
        carDao.deleteAll();
        gameDao.deleteAll();
    }
}
