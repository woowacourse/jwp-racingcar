package racingcar.dao.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.RacingCarDao;
import racingcar.entity.CarEntity;

import java.util.List;

@Repository
public class RacingCarWebDao implements RacingCarDao {

    private static final int FIRST_CAR = 0;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingCarWebDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarEntity> findCarsByGameId(int id) {
        String sqlForCarsByGameId = "SELECT * FROM RACING_CAR WHERE racing_game_id = ?";
        return jdbcTemplate.query(sqlForCarsByGameId, ObjectMapper.getCarEntityMapper(), id);
    }

    public void saveCar(CarEntity carEntity) {
        String sqlForSaveCars = "INSERT INTO RACING_CAR(name, position, racing_game_id) VALUES(?, ?, ?)";
        jdbcTemplate.update(sqlForSaveCars, carEntity.getName(), Integer.toString(carEntity.getPosition()), Integer.toString(carEntity.getRacingGameId()));
    }

    public int findIdByName(String name) {
        String sqlForFindId = "SELECT id FROM RACING_CAR WHERE name LIKE ?";
        return jdbcTemplate.query(sqlForFindId, ObjectMapper.getCarIdMapper(), name).get(FIRST_CAR);
    }

}
