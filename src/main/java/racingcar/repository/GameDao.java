package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

@Repository
public class GameDao {

    public static final String TABLE_NAME = "game";
    public static final String KEY_COLUMN_NAME = "id";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    @Autowired
    public GameDao(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(KEY_COLUMN_NAME)
                .usingColumns("trial_count");
    }

    public long insert(final int trialCount) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("trial_count", trialCount);

        return insertActor.executeAndReturnKey(params).longValue();
    }

    public int countAll() {
        String sql = "select count(*) from game";

        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
