package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PlayerDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAllPlayers(final Long id, final List<PlayerSaveDto> playerSaveDtos) {
        final String sql = "insert into Player (game_id, name, position, is_winner) "
                + "values (:gameId, :name, :position, :isWinner)";

        final SqlParameterSource[] params = playerSaveDtos.stream()
                .map(dto -> createParams(id, dto))
                .toArray(MapSqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, params);
    }

    private static MapSqlParameterSource createParams(final Long id, final PlayerSaveDto dto) {
        return new MapSqlParameterSource()
                .addValue("gameId", id)
                .addValue("name", dto.getName())
                .addValue("position", dto.getPosition())
                .addValue("isWinner", dto.getIsWinner());
    }
}
