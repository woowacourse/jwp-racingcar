package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameResultDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WinnerDao {

    private final JdbcTemplate jdbcTemplate;

    public WinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWinner(GameResultDto gameResultDto, long gameId) {
        String sql3 = "INSERT INTO winner (game_id,winner) VALUES (?,?)";
        List<String> winners = gameResultDto.getWinners();
        jdbcTemplate.batchUpdate(sql3, new BatchPreparedStatementSetter() {
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
