package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DbGameDao implements GameDao{
    private final JdbcTemplate jdbcTemplate;

    public DbGameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long save(int count) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO game (trial_count) VALUES (?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, count);
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        return (int) keys.get("id");
    }

    public List<Long> findAllId() {
        String sql = "SELECT id FROM game";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getLong("id"));
    }
}
