package racingcar.dao.car;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import racingcar.dao.dto.GameFinishedCarDto;
import racingcar.model.Car;

@Repository
public class JdbcTemplateCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(int gameId, List<Car> cars, List<String> winners) {
        String sql = "insert into CAR_RESULT (play_result_id, car_name, car_position, is_winner) values (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                cars,
                cars.size(),
                (PreparedStatement ps, Car car) -> {
                    final boolean isWinner = winners.contains(car.getName());
                    ps.setInt(1, gameId);
                    ps.setString(2, car.getName());
                    ps.setInt(3, car.getPosition());
                    ps.setBoolean(4, isWinner);
                });
    }

    @Override
    public Map<Integer, List<GameFinishedCarDto>> selectAll() {
        String sql = "SELECT play_result_id, car_name, car_position, is_winner FROM CAR_RESULT";

        return jdbcTemplate.query(sql, getGamePlayResponseExtractor);
    }

    private final ResultSetExtractor<Map<Integer, List<GameFinishedCarDto>>> getGamePlayResponseExtractor = resultSet -> {
        final Map<Integer, List<GameFinishedCarDto>> carsByGame = new HashMap<>();
        while (resultSet.next()) {
            final int gameId = resultSet.getInt("play_result_id");
            final String carName = resultSet.getString("car_name");
            final int carPosition = resultSet.getInt("car_position");
            final boolean isWinner = resultSet.getBoolean("is_winner");

            final List<GameFinishedCarDto> cars = carsByGame.getOrDefault(gameId, new ArrayList<>());
            cars.add(new GameFinishedCarDto(carName, carPosition, isWinner));
            carsByGame.put(gameId, cars);
        }
        return carsByGame;
    };
}
