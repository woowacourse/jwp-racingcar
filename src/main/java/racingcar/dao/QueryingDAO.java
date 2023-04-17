package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.vo.CarName;
import racingcar.domain.vo.Position;
import racingcar.dto.RacingResultResponse;
import racingcar.utils.RandomNumberGenerator;

@Repository
public class QueryingDAO {

    private JdbcTemplate jdbcTemplate;

    public QueryingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> findRacingIds() {
        String sql = "SELECT id FROM racing";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getInt("id"));
    }

    public List<String> findWinnersByRacingId(int id) {
        String sql = "SELECT name FROM car_info WHERE racing_id = ? AND is_winner = true";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getString("name")
            , id);
    }

    public Cars findCarsById(int id) {
        String sql = "SELECT name, position FROM car_info WHERE racing_id = ?";
        return new Cars(jdbcTemplate.query(sql,
            (resultSet, rowNum) -> new Car(
                CarName.of(resultSet.getString("name")),
                Position.of(resultSet.getInt("position"))
            ), id), RandomNumberGenerator.makeInstance());
    }

}
