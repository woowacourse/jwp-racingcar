package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

@TestPropertySource(locations = "/application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameDaoTest {

    @Autowired
    private GameDao gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    void beforeAll() {
        List<Object[]> trial_count = Arrays.asList(
                new String[]{"10"},
                new String[]{"20"}
        );
        jdbcTemplate.batchUpdate("INSERT INTO game(trial_count) VALUES (?)", trial_count);
    }

    @Test
    @DisplayName("값을 넣었을때 자동으로 증가되는 id 값을 반환하는 테스트")
    void 값을_넣었을때_자동으로_증가되는_id_값을_반환하는_테스트() {
        long id = gameDao.insert(10);

        assertThat(id).isEqualTo(3L);
    }

    @Test
    @DisplayName("테이블의 모든 행의 수를 반환한다")
    void 테이블의_모든_행의_수를_반환한다() {
        Integer result = gameDao.countAll().get();

        assertThat(result).isEqualTo(2);
    }
}
