package racingcar.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import racingcar.model.Car;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestPropertySource(locations = "/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RecordDaoTest {

    @Autowired
    private GameDao gameDao;
    @Autowired
    private RecordDao recordDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE;");
        jdbcTemplate.execute("TRUNCATE TABLE record RESTART IDENTITY;");
        jdbcTemplate.execute("TRUNCATE TABLE game RESTART IDENTITY;");
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE;");

        recordDao = new RecordDao(jdbcTemplate);

        gameDao.insert(10);
        gameDao.insert(20);

        recordDao.insert(1, false, new Car("a"));
        recordDao.insert(1, true, new Car("b"));
        recordDao.insert(2, false, new Car("c"));
        recordDao.insert(2, true, new Car("d"));
    }

    @Test
    @DisplayName("단일 Record 삽입 테스트")
    void 단일_Record_삽입_테스트() {
        Car doggy = new Car("doggy");

        assertThatNoException().isThrownBy(
                () -> recordDao.insert(2, true, doggy)
        );
    }

    @Test
    @DisplayName("존재하지 않는 gameId가 들어온 경우 예외가 발생한다")
    void 존재하지_않는_gameId가_들어온_경우_예외가_발생한다() {
        assertThatThrownBy(
                () -> recordDao.insert(3, false, new Car("doggy"))
        );
    }

    @Test
    @DisplayName("이미 존재하는 game_id와 player_name이 동시에 들어온 경우 예외가 발생한다")
    void 이미_존재하는_game_id와_player_name이_동시에_들어온_경우_예외가_발생한다() {
        Car c = new Car("c");

        assertThatThrownBy(() -> recordDao.insert(2, false, c));

    }

    @Test
    @DisplayName("모든 결과 값을 반환")
    void 모든_결과_값을_반환() {
        assertThatNoException().isThrownBy(
                () -> recordDao.findAll()
        );
    }
}
