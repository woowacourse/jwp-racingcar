package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dto.WinnerDto;

public class WinnerDao {
    private final SimpleJdbcInsert insertActor;

    public WinnerDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
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
}
