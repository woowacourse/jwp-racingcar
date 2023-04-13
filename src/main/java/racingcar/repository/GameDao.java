package racingcar.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class GameDao {
    public static final String TABLE_NAME = "game";
    public static final String KEY_COLUMN_NAME = "id";

    private final SimpleJdbcInsert insertActor;

    public GameDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns(KEY_COLUMN_NAME)
                .usingColumns("trial_count");
    }

    public long insert(final int trial_count) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("trial_count", trial_count);

        return insertActor.executeAndReturnKey(params).longValue();
    }
}
