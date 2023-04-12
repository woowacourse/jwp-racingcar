package racingcar.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CarsDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public CarsDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("cars")
                .usingGeneratedKeyColumns("id");
    }

    public int insert(int gameId, String name, int position) {
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("game_id", gameId)
                .addValue("name", name)
                .addValue("position", position);

        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }
}
