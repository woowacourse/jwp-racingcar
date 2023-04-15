package racingcar;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarResultDto;

@Repository
public class PlayerSelectDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PlayerSelectDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RacingCarResultDto> selectBy(final int gameId) {
        final String sql = "SELECT * FROM player WHERE game_id = :game_id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("game_id", gameId);

        final RowMapper<RacingCarResultDto> carMapper = (resultSet, rowNum) -> {
            final String name = resultSet.getString("name");
            final int position = resultSet.getInt("position");
            return new RacingCarResultDto(name, position);
        };

        return jdbcTemplate.query(sql, namedParameters, carMapper);
    }

    public List<String> selectWinnerBy(final int gameId) {
        final String sql = "SELECT * FROM player WHERE game_id = :game_id AND is_winner = true";
        SqlParameterSource namedParameters = new MapSqlParameterSource("game_id", gameId);

        return jdbcTemplate.query(sql, namedParameters,
                (resultSet, rowCount) -> resultSet.getString("name"));
    }
}
