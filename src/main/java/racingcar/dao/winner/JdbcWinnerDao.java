package racingcar.dao.winner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import racingcar.dao.entity.Winner;

@Repository
public class JdbcWinnerDao implements WinnerDao {
    private static final RowMapper<Winner> WINNER_ROW_MAPPER = (resultSet, row) -> {
        long gameId = resultSet.getLong("g_id");
        String winner = resultSet.getString("winner");
        return new Winner(gameId, winner);
    };
    private final JdbcTemplate jdbcTemplate;

    public JdbcWinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertWinner(String winners, long gameId) {
        String sql = "INSERT INTO winner (g_id,winner) VALUES (?,?)";
        String[] winnerNames = winners.split(", ");
        jdbcTemplate.batchUpdate(sql, getWinnerNames(gameId, winnerNames));
    }

    private List<Object[]> getWinnerNames(long gameId, String[] winners) {
        return Arrays.stream(winners)
                .map(winner -> {
                    Object[] winnerName = new Object[2];
                    winnerName[0] = gameId;
                    winnerName[1] = winner;
                    return winnerName;
                })
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Winner> findAllWinner() {
        String sql = "SELECT g_id, winner FROM winner";
        return jdbcTemplate.query(sql, WINNER_ROW_MAPPER);
    }
}
