package racingcar.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PlayerDaoImpl(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAllPlayers(final Long id, final List<Player> players) {
        final String sql = "insert into Player (game_id, name, position, is_winner) "
                + "values (:gameId, :name, :position, :isWinner)";

        final SqlParameterSource[] params = players.stream()
                .map(dto -> createParams(id, dto))
                .toArray(MapSqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(sql, params);
    }

    private static MapSqlParameterSource createParams(final Long id, final Player dto) {
        return new MapSqlParameterSource()
                .addValue("gameId", id)
                .addValue("name", dto.getName())
                .addValue("position", dto.getPosition())
                .addValue("isWinner", dto.getIsWinner());
    }

    @Override
    public List<Player> findAll() {
        final String sql = "select id, game_id as gameId, name, position, is_winner as isWinner from player";

        final RowMapper<Player> rowMapper = BeanPropertyRowMapper.newInstance(Player.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
