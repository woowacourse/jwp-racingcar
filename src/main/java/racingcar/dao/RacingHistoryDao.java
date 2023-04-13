package racingcar.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingGameResultDto;

@Repository
public class RacingHistoryDao {
    private final SimpleJdbcInsert insertActor;

    public RacingHistoryDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("play_result")
                .usingGeneratedKeyColumns("id");
    }

    public int insertResult(final RacingGameResultDto racingGameResultDto) {
        final Map<String, Object> params = new HashMap<>();
        params.put("winners", racingGameResultDto.getWinners());
        params.put("play_count", racingGameResultDto.getPlayCount());
        params.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(params).intValue();
    }
}
