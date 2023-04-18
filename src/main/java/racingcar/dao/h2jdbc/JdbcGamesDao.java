package racingcar.dao.h2jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.GamesDao;

import java.util.List;

@Repository
public class JdbcGamesDao implements GamesDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public JdbcGamesDao(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("games")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(final int trialCount) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("trial_count", trialCount);

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    @Override
    public List<Integer> findAllGameIds() {
        final String sql = "SELECT id FROM games ORDER BY id DESC";

        return jdbcTemplate.queryForList(sql, Integer.class);
    }
}
