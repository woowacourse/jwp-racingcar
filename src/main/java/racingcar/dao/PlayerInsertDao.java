package racingcar.dao;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

@Repository
public class PlayerInsertDao {

    private final SimpleJdbcInsert insertPlayerActor;

    public PlayerInsertDao(DataSource dataSource) {
        this.insertPlayerActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAYER")
                .usingGeneratedKeyColumns("id");
    }

    public void insertPlayers(int gameId, List<Car> cars, List<String> winnerNames) {
        cars.forEach(car -> insertPlayer(gameId, car, isWinner(winnerNames, car.getName())));
    }

    private int isWinner(List<String> winnerNames, String name) {
        if (winnerNames.contains(name)) {
            return 1;
        }
        return 0;
    }

    private void insertPlayer(int gameId, Car car, int isWinner) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("game_id", gameId);
        parameters.put("name", car.getName());
        parameters.put("position", car.getDistance());
        parameters.put("isWinner", isWinner);
        insertPlayerActor.execute(parameters);
    }
}
