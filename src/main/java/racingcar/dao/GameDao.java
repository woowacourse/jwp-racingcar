package racingcar.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameDto;

@Repository
public class GameDao {

    private final SimpleJdbcInsert insertActor;

    public GameDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    public long insertGame(final GameDto gameDto) {
        final Map<String, Object> params = new HashMap<>();
        params.put("play_count", gameDto.getPlayCount());
        params.put("created_at", LocalDateTime.now());
        return insertActor.executeAndReturnKey(params).longValue();
    }
}
