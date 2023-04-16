package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@DatabaseTest
public class ConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DisplayName("연결테스트")
    @Test
    void test_() {
        jdbcTemplate.execute("INSERT INTO gamestates(initial_trial_count, remaining_trial_count) VALUES (2, 2)");

        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("SELECT * FROM gamestates WHERE id = 1");
        assertThat(stringObjectMap).hasSize(4);
    }
}
