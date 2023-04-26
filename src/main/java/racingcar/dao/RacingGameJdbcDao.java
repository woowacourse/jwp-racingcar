package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.RacingGameEntity;

@Component
public class RacingGameJdbcDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public RacingGameJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("racing_game")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Long save(RacingGameEntity racingGameEntity) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(racingGameEntity);
        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
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
