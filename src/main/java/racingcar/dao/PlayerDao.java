package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dto.PlayerDto;

public class PlayerDao {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public PlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player")
                .usingGeneratedKeyColumns("id");
    }

    public void insertPlayers(final List<PlayerDto> players, final long gameId) {
        for (final PlayerDto player : players) {
            final Map<String, Object> params = new HashMap<>();
            params.put("game_id", gameId);
            params.put("name", player.getName());
            params.put("position", player.getPosition());
            insertActor.execute(params);
        }
    }

    public List<PlayerDto> findAllByGameId(final long gameId) {
        final String sql = "SELECT name, position FROM PLAYER WHERE game_id = ?";
        return jdbcTemplate.query(sql, playerDtoRowMapper(), gameId);
    }

    private RowMapper<PlayerDto> playerDtoRowMapper() {
        return (rs, rowNum) -> new PlayerDto(rs.getString("name"), rs.getInt("position"));
    }
}
