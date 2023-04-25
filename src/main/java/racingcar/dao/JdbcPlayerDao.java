package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerEntity;

import java.util.List;

@Repository
public class JdbcPlayerDao extends JdbcTemplateDao implements PlayerDao {

    public JdbcPlayerDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void save(final long gameId, final List<PlayerEntity> playerEntities) {
        final String sql = "insert into Player (game_id, name, position, is_winner) "
                + "values (:gameId, :name, :position, :isWinner)";

        final MapSqlParameterSource[] params = playerEntities.stream()
                .map(dto -> createParams(gameId, dto))
                .toArray(MapSqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, params);
    }

    private MapSqlParameterSource createParams(final long id, final PlayerEntity dto) {
        return new MapSqlParameterSource()
                .addValue("gameId", id)
                .addValue("name", dto.getName())
                .addValue("position", dto.getPosition())
                .addValue("isWinner", dto.getIsWinner());
    }

    @Override
    public List<PlayerEntity> findById(long gameId) {
        final String sql = "select id, game_id, name, position, is_winner from Player where game_id = :game_id";
        final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("game_id", gameId);
        return jdbcTemplate.query(sql, sqlParameterSource,
                (resultSet, rowNum) -> new PlayerEntity(
                        resultSet.getLong("id"),
                        resultSet.getLong("game_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("position"),
                        resultSet.getBoolean("is_winner")
                ));
    }
}
