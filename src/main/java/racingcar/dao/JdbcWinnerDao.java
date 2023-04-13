package racingcar.dao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import racingcar.dto.ResultDto;

@Repository
public class JdbcWinnerDao implements WinnerDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcWinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertWinner(ResultDto resultDto, long gameId) {
        String sql = "INSERT INTO winner (g_id,winner) VALUES (?,?)";
        String[] winners = resultDto.getWinners().split(", ");
        jdbcTemplate.batchUpdate(sql, getWinnerNames(gameId, winners));
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
}
