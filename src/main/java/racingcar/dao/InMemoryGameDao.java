package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.GameEntity;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class InMemoryGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public InMemoryGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(int trialCount) {
        String sql = "insert into PLAY_RESULT (trial_Count) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, trialCount);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<GameEntity> findAll() {
        String sql = "select * from PLAY_RESULT";

        return jdbcTemplate.query(sql, (result, id) ->
                new GameEntity(result.getInt("id"),
                        result.getInt("trial_count"))
        );
    }
}
