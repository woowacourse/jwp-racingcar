package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.CarEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class RacingCarDao {

    private static final int FIRST_CAR = 0;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarEntity> findCarsByGameId(int id) {
        String sqlForCarsByGameId = "SELECT * FROM RACING_CAR WHERE racing_game_id = ?";
        return jdbcTemplate.query(sqlForCarsByGameId, ObjectMapper.getCarEntityMapper(), id);
    }

    public void saveCar(int gameId, Car car) {
        String sqlForSaveCars = "INSERT INTO RACING_CAR(name, position, racing_game_id) VALUES(?, ?, ?)";
        jdbcTemplate.update(sqlForSaveCars, car.getName(), Integer.toString(car.getPosition()), Integer.toString(gameId));
    }

    public int findIdByName(String name) {
        String sqlForFindId = "SELECT id FROM RACING_CAR WHERE name LIKE ?";
        return jdbcTemplate.query(sqlForFindId, ObjectMapper.getCarIdMapper(), name).get(FIRST_CAR);
    }

}
