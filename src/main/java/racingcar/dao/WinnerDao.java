package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dto.WinnerDto;

public class WinnerDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public WinnerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner")
                .usingGeneratedKeyColumns("id");
    }

    public void insertWinners(final List<WinnerDto> winners, final long gameId) {
        for (final WinnerDto winner : winners) {
            final Map<String, Object> params = new HashMap<>();
            params.put("game_id", gameId);
            params.put("name", winner.getName());
            insertActor.execute(params);
        }
    }

    public List<WinnerDto> findAllByGameId(final long gameId) {
        final String sql = "SELECT name FROM WINNER WHERE game_id = ?";
        return jdbcTemplate.query(sql, playerDtoRowMapper(), gameId);
    }

    private RowMapper<WinnerDto> playerDtoRowMapper() {
        return (rs, rowNum) -> new WinnerDto(rs.getString("name"));
    }
}
