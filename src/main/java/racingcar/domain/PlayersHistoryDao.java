package racingcar.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PlayersHistoryDao {
    private final SimpleJdbcInsert insertActor;

    public PlayersHistoryDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("players_result")
                .usingGeneratedKeyColumns("id");
    }

    public void insertResult(final List<RacingCarDto> racingCarDtos, final int resultId) {
        for (final RacingCarDto racingCarDto : racingCarDtos) {
            final Map<String, Object> params = new HashMap<>();
            params.put("name", racingCarDto.getName());
            params.put("position", racingCarDto.getPosition());
            params.put("result_id", resultId);
            insertActor.execute(params);
        }
    }
}
