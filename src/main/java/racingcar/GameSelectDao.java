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

    public List<String> gameIds() {
        final String sql = "SELECT (game_id) from GAME";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getString("game_id"));
    }
}
