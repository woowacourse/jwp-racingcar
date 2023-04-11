package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DisplayName("연결테스트")
    @Test
    void test_() {
        jdbcTemplate.execute("INSERT INTO games(trial_count) VALUES (2)");

        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("SELECT * FROM games WHERE id = 1");
        assertThat(stringObjectMap).hasSize(3);
    }
}
