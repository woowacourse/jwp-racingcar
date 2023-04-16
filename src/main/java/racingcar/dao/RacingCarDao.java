package racingcar.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.Car;

@Repository
public class RacingCarDao {

    private final SimpleJdbcInsert insertActor;

    public RacingCarDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_car")
                .usingGeneratedKeyColumns("id");
    }

    public Long save(final Long gameId, final Car car) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("game_id", gameId);
        parameters.put("name", car.getName());
        parameters.put("position", car.getPosition());
        return insertActor.executeAndReturnKey(parameters).longValue();
    }
}
