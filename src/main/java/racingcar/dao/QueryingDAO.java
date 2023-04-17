package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueryingDAO {

    private JdbcTemplate jdbcTemplate;

    public QueryingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> findWinnersByRacingId(int id) {
        String sql = "SELECT name FROM car_info WHERE racing_id = ? AND is_winner = true";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getString("name")
            , id);
    }

}
