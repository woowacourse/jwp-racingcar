package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WinnerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public WinnerDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insertAll(final int raceId, final List<Integer> winnerCarIds) {
        winnerCarIds.forEach(winnerCarId -> insert(raceId, winnerCarId));
    }

    public void insert(final int raceId, final Integer winnerCarId) {
        final String sql = "INSERT INTO WINNER(race_id, player_id) values (:race_id, :player_id)";

        final MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("race_id", raceId)
            .addValue("player_id", winnerCarId);
        namedParameterJdbcTemplate.update(sql, params);
    }
}
