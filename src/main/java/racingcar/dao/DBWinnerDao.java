package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DBWinnerDao {

    private final JdbcTemplate jdbcTemplate;

    public DBWinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWinner(List<String> winners, long gameId) {
        String sql = "INSERT INTO winner (game_id,winner) VALUES (?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, gameId);
                ps.setString(2, winners.get(i));
            }

            @Override
            public int getBatchSize() {
                return winners.size();
            }
        });
    }

    public List<String> selectByGameId(long gameId) {
        String sql = "SELECT winner FROM winner WHERE game_id = ?";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getString("winner"), gameId);
    }
}
