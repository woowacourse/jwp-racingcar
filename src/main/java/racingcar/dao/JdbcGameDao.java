package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
class JdbcGameDao extends JdbcTemplateDao implements GameDao{

    public JdbcGameDao(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Long save(int trialCount) {
        final String sql = "insert into Game (trial_count) values (:trialCount)";
        final SqlParameterSource gameParameters = new MapSqlParameterSource("trialCount", trialCount);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, gameParameters, keyHolder);
        return (long) keyHolder.getKeys().get("id");
    }
}
