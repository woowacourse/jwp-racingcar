package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcGameDao implements GameDao {

    private final SimpleJdbcInsert insertGameActor;
    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.insertGameActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("GAME")
                .usingGeneratedKeyColumns("game_id")
                .usingColumns("winners", "trial_count");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(String winners, Integer count) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("winners", winners);
        parameters.put("trial_count", count);
        return insertGameActor.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public List<Integer> findAllIds() {
        String sql = "SELECT game_id FROM GAME";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Override
    public String findWinners(int gameId) {
        String sql = "SELECT winners FROM GAME WHERE game_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, gameId);
    }
}
