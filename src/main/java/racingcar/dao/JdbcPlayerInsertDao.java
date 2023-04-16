package racingcar.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcPlayerInsertDao implements PlayerInsertDao {

    private final SimpleJdbcInsert insertPlayerActor;

    public JdbcPlayerInsertDao(DataSource dataSource) {
        this.insertPlayerActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAYER")
                .usingGeneratedKeyColumns("player_id");
    }

    @Override
    public void insertPlayers(int gameId, List<Car> cars) {
        SqlParameterSource[] batchParams = new SqlParameterSource[cars.size()];
        for (int index = 0; index < cars.size(); index++) {
            Car car = cars.get(index);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("game_id", gameId);
            parameters.put("name", car.getName());
            parameters.put("position", car.getDistance());
            batchParams[index] = new MapSqlParameterSource(parameters);
        }

        insertPlayerActor.executeBatch(batchParams);
    }
}
