package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class WinnerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public WinnerDao(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insertAll(final long raceId, final List<Integer> winnerCarIds) {
        winnerCarIds.forEach(winnerCarId -> insert(raceId, winnerCarId));
    }

    public void insert(final long raceId, final int winnerCarId) {
        final String sql = "INSERT INTO WINNER(race_id, player_id) values (:race_id, :player_id)";

        final MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("race_id", raceId)
            .addValue("player_id", winnerCarId);
        namedParameterJdbcTemplate.update(sql, params);
    }
    
    public List<Long> findWinnerIdsByRaceId(long raceId) {
        String sql = "SELECT player_id FROM WINNER WHERE race_id=:race_id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("race_id", raceId);
        
        return namedParameterJdbcTemplate.queryForList(sql, params).stream()
                .map(stringObjectMap -> stringObjectMap.get("player_id"))
                .map(String::valueOf)
                .map(Long::parseLong)
                .collect(Collectors.toUnmodifiableList());
    }
}
