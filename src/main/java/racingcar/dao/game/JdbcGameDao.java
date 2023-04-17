package racingcar.dao.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcGameDao implements GameDao {
    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trialCount")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public long saveGame(int trialCount) {
        Map<String, Object> map = new HashMap<>();
        map.put("trialCount", trialCount);
        return insertActor.executeAndReturnKey(map).longValue();
    }

    @Override
    public List<Long> getGameIds() {
        String sql = "SELECT id FROM game";
        return jdbcTemplate.queryForList(sql, Long.class);
    }
}
