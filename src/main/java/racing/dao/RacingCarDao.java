package racing.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racing.controller.dto.request.CarRequest;

import java.util.List;

@Repository
public class RacingCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<CarEntity> rowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getString("name"),
            resultSet.getInt("position"),
            resultSet.getBoolean("is_winner"),
            resultSet.getLong("game_id")
    );

    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(CarRequest car) {
        String saveCarQuery = "INSERT INTO car(name, position, is_winner, game_id) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(saveCarQuery,
                car.getCarName(),
                car.getPosition(),
                car.isWinner(),
                car.getGameId()
        );
    }

    @Override
    public List<CarEntity> findAll() {
        final String query = "select * from car";
        return jdbcTemplate.query(query, rowMapper);
    }
}
