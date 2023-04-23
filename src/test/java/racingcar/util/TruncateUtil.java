package racingcar.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TruncateUtil {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void truncate(String tableName) {
        String sql = "TRUNCATE TABLE " + tableName;

        jdbcTemplate.update(sql);
    }
}
