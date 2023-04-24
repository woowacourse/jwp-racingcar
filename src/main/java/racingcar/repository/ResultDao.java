package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.Result;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ResultDao {

    private final JdbcTemplate jdbcTemplate;

    public ResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long insert(int trialCount, String winners) {
        String sql = "insert into results (trial_count, winners) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, trialCount);
            ps.setString(2, winners);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private final RowMapper<Result> actorRowMapper = (resultSet, rowNum) -> {
        Result result = new Result(
                resultSet.getLong("id"),
                resultSet.getInt("trial_count"),
                resultSet.getString("winners"),
                resultSet.getTimestamp("created_at")
                         .toLocalDateTime()
        );
        return result;
    };

    public List<Result> findAll() {
        String sql = "select * from results";
        return jdbcTemplate.query(sql,actorRowMapper);
    }
}
