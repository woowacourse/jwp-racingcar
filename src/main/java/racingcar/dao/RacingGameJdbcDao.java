package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.RacingGameEntity;

@Component
public class RacingGameJdbcDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(RacingGameEntity racingGameEntity) {
        String sql = "INSERT INTO racing_game (trial_count, created_at) VALUES (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, racingGameEntity.getTrialCount());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(racingGameEntity.getCreatedTime()));
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public List<RacingGameEntity> findAllByCreatedTimeAsc() {
        return jdbcTemplate.query(
                "SELECT * FROM racing_game ORDER BY created_at DESC ",
                (resultSet, rowNum) -> new RacingGameEntity(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                ));
    }
}
