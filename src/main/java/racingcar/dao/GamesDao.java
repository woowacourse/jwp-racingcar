package racingcar.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class GamesDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public GamesDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("games")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(final int trialCount) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("trial_count", trialCount);

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }
}
