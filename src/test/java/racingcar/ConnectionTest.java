package racingcar;

import static org.assertj.core.api.Assertions.assertThatNoException;

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
        String sql = "select * from gamestates";

        assertThatNoException()
                .isThrownBy(() -> jdbcTemplate.execute(sql));
    }
}
