package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GamesDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public GamesDao(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("games")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(final int trialCount) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("trial_count", trialCount);

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    public List<Integer> findAllGameIds() {
        final String sql = "SELECT id FROM games";

        return jdbcTemplate.queryForList(sql, Integer.class);
    }
}
