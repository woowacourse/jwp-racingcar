package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.controller.ApplicationType;
import racingcar.entity.GameEntity;

import java.util.List;

@Repository
class JdbcGameDao extends JdbcTemplateDao implements GameDao {

    public JdbcGameDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Long save(final GameEntity gameEntity) {
        final String sql = "insert into Game (trial_count, application_id) values (:trialCount, :application_id)";
        final SqlParameterSource gameParameters = new MapSqlParameterSource()
                .addValue("trialCount", gameEntity.getTrialCount())
                .addValue("application_id", gameEntity.getApplicationType().getApplicationId());

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, gameParameters, keyHolder);

        return Long.parseLong(String.valueOf(keyHolder.getKeys().get("GENERATED_KEY")));
    }

    @Override
    public List<GameEntity> findAll() {
        final String sql = "select id, trial_count, created_at, application_id from Game";

        return jdbcTemplate.query(sql,
                (resultSet, rowNum) -> new GameEntity(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"),
                        resultSet.getDate("created_at"),
                        ApplicationType.findById(resultSet.getInt("application_id"))
                ));
    }
}
