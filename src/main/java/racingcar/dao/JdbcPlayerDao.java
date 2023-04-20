package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.PlayerFindDto;
import racingcar.dto.PlayerSaveDto;

import java.util.List;

@Repository
public class JdbcPlayerDao extends JdbcTemplateDao implements PlayerDao{

    public JdbcPlayerDao(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void save(final long gameId, final List<PlayerSaveDto> playerSaveDtos) {
        final String sql = "insert into Player (game_id, name, position, is_winner) "
                + "values (:gameId, :name, :position, :isWinner)";

        final MapSqlParameterSource[] params = playerSaveDtos.stream()
                .map(dto -> createParams(gameId, dto))
                .toArray(MapSqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, params);
    }

    @Override
    public List<PlayerFindDto> findById(long gameId) {
        final String sql = "select id, game_id, name, position, is_winner from Player where game_id = :game_id";
        final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("game_id", gameId);
        return jdbcTemplate.query(sql, sqlParameterSource,
                (resultSet, rowNum) -> new PlayerFindDto(
                        resultSet.getLong("id"),
                        resultSet.getLong("game_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("position"),
                        resultSet.getBoolean("is_winner")
                ));
    }

    private MapSqlParameterSource createParams(final long id, final PlayerSaveDto dto) {
        return new MapSqlParameterSource()
                .addValue("gameId", id)
                .addValue("name", dto.getName())
                .addValue("position", dto.getPosition())
                .addValue("isWinner", dto.getIsWinner());
    }
}
