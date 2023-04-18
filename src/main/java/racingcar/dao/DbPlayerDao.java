package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import racingcar.dto.ResultDto;

@Repository
public final class DbPlayerDao implements PlayerDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DbPlayerDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPlayer(List<ResultDto> responses, List<String> winnerNames, int gameId) {
        String sql = "INSERT INTO player(name, position, game_id, is_winner) VALUES(:name, :position, :game_id, :is_winner)";

        System.out.println(gameId);
        for (ResultDto response : responses) {
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("name", response.getName())
                    .addValue("position", response.getPosition())
                    .addValue("game_id", gameId)
                    .addValue("is_winner", winnerNames.contains(response.getName()));

            jdbcTemplate.update(sql, namedParameters);
        }
    }

    public List<ResultDto> selectBy(final int gameId) {
        final String sql = "SELECT * FROM player WHERE game_id = :game_id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("game_id", gameId);

        final RowMapper<ResultDto> carMapper = (resultSet, rowNum) -> {
            final String name = resultSet.getString("name");
            final int position = resultSet.getInt("position");
            return new ResultDto(name, position);
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
