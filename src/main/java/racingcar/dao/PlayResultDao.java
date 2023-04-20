package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.PlayResultDto;
import racingcar.entity.PlayResultEntity;
import racingcar.utils.mapper.EntityToMap;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayResultDao {
    private final SimpleJdbcInsert insertPlayResult;
    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        this.insertPlayResult = new SimpleJdbcInsert(dataSource)
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

    public int insertResult(final PlayResultDto playResultDto) {
        return insertPlayResult.executeAndReturnKey(
                EntityToMap.convert(dto -> {
                            Map<String, Object> params = new HashMap<>();
                            params.put("winners", playResultDto.getWinners());
                            params.put("play_count", playResultDto.getPlayCount());
                            params.put("created_at", LocalDateTime.now());
                            return params;
                        }
                        , playResultDto)).intValue();
    }

    public List<PlayResultEntity> getResult() {
        String sql = "select * from play_result";
        return jdbcTemplate.query(sql, playResultRowMapper);
    }
}
