package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RacingCarDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public RacingCarDao(final DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_car")
                .usingGeneratedKeyColumns("id");
    }

    public void saveAll(final Long gameId, final List<Car> cars) {
        final SqlParameterSource[] batchParameters = cars.stream()
                .map(car -> new MapSqlParameterSource()
                        .addValue("game_id", gameId)
                        .addValue("name", car.getName())
                        .addValue("position", car.getPosition()))
                .toArray(MapSqlParameterSource[]::new);


        simpleJdbcInsert.executeBatch(batchParameters);
    }
}
