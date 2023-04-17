package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayResult;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlayResultJdbcDao implements PlayResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long insert(final String winners, final int trialCount) {
        String sql = "insert into PLAY_RESULT (winners, trial_count) values (?, ?)";

        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, winners);
            ps.setInt(2, trialCount);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<PlayResult> findAllPlayResult() {
        String sql = "select * from PLAY_RESULT";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    final PlayResult playResult = new PlayResult(
                            resultSet.getLong("id"),
                            resultSet.getString("winners"),
                            resultSet.getInt("trial_count"),
                            resultSet.getTimestamp("created_at").toLocalDateTime()
                    );
                    return playResult;
                });
    }
}
