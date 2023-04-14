package racingcar.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameDto;
import racingcar.dto.GameHistoryDto;

@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public GameDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    public long insertGame(final GameDto gameDto) {
        final Map<String, Object> params = new HashMap<>();
        params.put("play_count", gameDto.getPlayCount());
        params.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(params).longValue();
    }

    public List<GameHistoryDto> findAllHistory() {
        final String sql = "SELECT id, play_count FROM GAME";
        return jdbcTemplate.query(sql, idRowMapper());
    }

    private RowMapper<GameHistoryDto> idRowMapper() {
        return (rs, rowNum) -> new GameHistoryDto(rs.getLong("id"), rs.getInt("play_count"));
    }
}
