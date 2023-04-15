package racingcar;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GameSelectDao {

    private final JdbcTemplate jdbcTemplate;

    public GameSelectDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> gameIds() {
        final String sql = "SELECT (id) from GAME";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getInt("id"));
    }
}
