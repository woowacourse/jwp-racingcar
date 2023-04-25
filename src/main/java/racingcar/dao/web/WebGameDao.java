package racingcar.dao.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.GameDao;

@Repository
public class WebGameDao implements GameDao {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public WebGameDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertGame(final int tryTimes) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("trial_count", tryTimes);
        parameters.put("created_at", Timestamp.valueOf(LocalDateTime.now()));

        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    public List<Integer> findAllGamesId() {
        String sql = "SELECT id FROM game";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }
}
