package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.GameEntity;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GameDao {

    private final JdbcTemplate jdbcTemplate;

    public GameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long save(final String winners, final int trialCount) {
        String sql = "insert into GAME (winners, trial_count) values (?, ?)";

        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, winners);
            ps.setInt(2, trialCount);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<GameEntity> findAllWinners() {
        final String sql = "SELECT * FROM GAME";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            final long id = rs.getLong("id");
            final String winners = rs.getString("winners");

            return new GameEntity(id, winners);
        });
    }
}
