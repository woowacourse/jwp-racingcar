package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dto.db.GameResultDto;
import racingcar.dto.db.GameResultWithCarDto;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public final class GameResultDao {

    private final JdbcTemplate jdbcTemplate;

    public GameResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<GameResultWithCarDto> actorRowMapper = (resultSet, rowNum) -> {
        return new GameResultWithCarDto(
                resultSet.getLong("id"),
                resultSet.getInt("try_count"),
                resultSet.getString("player_name"),
                resultSet.getInt("final_position"),
                resultSet.getBoolean("is_winner")
        );
    };

    public Long save(final GameResultDto dto) {
        String sql = "INSERT INTO game_result (try_count) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, dto.getTryCount());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Map<Long, List<GameResultWithCarDto>> findAll() {
        String sql = "SELECT gr.id, gr.try_count, c.player_name, c.final_position, c.is_winner " +
                "FROM game_result as gr " +
                "JOIN car as c " +
                "ON gr.id = c.game_result_id " +
                "ORDER BY gr.id";

        return jdbcTemplate.query(sql, actorRowMapper)
                .stream()
                .collect(Collectors.groupingBy(GameResultWithCarDto::getId));
    }
}
