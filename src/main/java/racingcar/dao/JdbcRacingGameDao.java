package racingcar.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.exception.DatabaseAccessException;

import java.util.List;

@Repository
public class JdbcRacingGameDao implements RacingGameDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcRacingGameDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static MapSqlParameterSource createParams(final Long id, final PlayerSaveDto dto) {
        return new MapSqlParameterSource()
                .addValue("gameId", id)
                .addValue("name", dto.getName())
                .addValue("position", dto.getPosition())
                .addValue("isWinner", dto.getIsWinner());
    }

    @Override
    public void save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos) {
        final Long gameId = saveGame(trialCount);
        saveAllPlayers(gameId, playerSaveDtos);
    }

    private Long saveGame(final int trialCount) {
        final String sql = "insert into Game (trial_count) values (:trialCount)";
        final SqlParameterSource gameParameters = new MapSqlParameterSource("trialCount", trialCount);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(sql, gameParameters, keyHolder);
            return (long) keyHolder.getKeys().get("id");
        } catch (DataAccessException exception) {
            throw new DatabaseAccessException();
        }
    }

    private void saveAllPlayers(final Long id, final List<PlayerSaveDto> playerSaveDtos) {
        final String sql = "insert into Player (game_id, name, position, is_winner) "
                + "values (:gameId, :name, :position, :isWinner)";

        final MapSqlParameterSource[] params = playerSaveDtos.stream()
                .map(dto -> createParams(id, dto))
                .toArray(MapSqlParameterSource[]::new);
        try {
            jdbcTemplate.batchUpdate(sql, params);
        } catch (DatabaseAccessException exception) {
            throw new DatabaseAccessException();
        }
    }
}
