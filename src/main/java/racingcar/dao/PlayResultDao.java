package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingGameDto;
import racingcar.entity.PlayResultEntity;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayResultDao {
    private final SimpleJdbcInsert insertRacingGame;
    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        this.insertRacingGame = new SimpleJdbcInsert(dataSource)
                .withTableName("play_result")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayResultEntity> playResultRowMapper = (resultSet, rowNum) -> {
        PlayResultEntity playResultEntity = new PlayResultEntity(
                resultSet.getInt("id"),
                resultSet.getString("winners"),
                resultSet.getInt("play_count"),
                resultSet.getTimestamp("created_at")
        );
        return playResultEntity;
    };

    public int insertResult(final RacingGameDto racingGameDto) {
        final Map<String, Object> params = new HashMap<>();
        params.put("winners", racingGameDto.getWinners());
        params.put("play_count", racingGameDto.getPlayCount());
        params.put("created_at", LocalDateTime.now());
        return insertRacingGame.executeAndReturnKey(params).intValue();
    }

    public List<PlayResultEntity> getResult() {
        String sql = "select * from play_result";
        return jdbcTemplate.query(sql, playResultRowMapper);
    }
}
