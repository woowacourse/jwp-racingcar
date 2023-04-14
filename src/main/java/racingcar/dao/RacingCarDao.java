package racingcar.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RacingCarDao {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public RacingCarDao(final DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_car")
                .usingGeneratedKeyColumns("id");
    }

    public Long save(final Long gameId, final Car car) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("game_id", gameId);
        parameters.put("name", car.getName());
        parameters.put("position", car.getPosition());
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }
}
