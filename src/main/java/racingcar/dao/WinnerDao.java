package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.ResultDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class WinnerDao {

    private final JdbcTemplate jdbcTemplate;

    public WinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertWinner(ResultDto resultDto, long gameId) {
        String sql3 = "INSERT INTO winner (game_id,winner) VALUES (?,?)";
        String[] winners = resultDto.getWinners().split(", ");
        jdbcTemplate.batchUpdate(sql3, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, gameId);
                ps.setString(2, winners[i]);
            }

            @Override
            public int getBatchSize() {
                return winners.length;
            }
        });
    }
}
