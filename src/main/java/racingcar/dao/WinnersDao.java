package racingcar.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class WinnersDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public WinnersDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("winners");
    }

    public void insert(final int gameId, final List<Integer> winnerIds) {
        for (Integer winnerId : winnerIds) {
            final SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("game_id", gameId)
                    .addValue("car_id", winnerId);

            simpleJdbcInsert.execute(parameterSource);
        }
    }
}
