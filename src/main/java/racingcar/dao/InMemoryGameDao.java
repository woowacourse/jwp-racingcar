package racingcar.dao;

import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public InMemoryGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(int tryCount) {
        String sql = "insert into PLAY_RESULT (trial_Count) values (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, tryCount);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
