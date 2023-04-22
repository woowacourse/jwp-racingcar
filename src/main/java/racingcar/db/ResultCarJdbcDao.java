package racingcar.db;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.exception.BusinessArgumentException;
import racingcar.exception.ErrorCode;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultCarJdbcDao implements ResultCarDao {
    private final JdbcTemplate jdbcTemplate;

    public ResultCarJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(int gameId, List<Car> cars) {
        String sql = "insert into RESULT_CAR (name, position, game_id) values (?, ?, ?)";

        List<Object[]> resultCars = new ArrayList<>();
        for (Car car : cars) {
            resultCars.add(new Object[]{car.getName(), car.getPosition(), gameId});
        }

        try {
            jdbcTemplate.batchUpdate(sql, resultCars);
        } catch (DataAccessException e) {
            throw new BusinessArgumentException(ErrorCode.GAME_NOT_EXIST);
        }
    }

    @Override
    public List<Car> findByGameId(int gameId) {
        String sql = "select name,position from RESULT_CAR where game_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Car(new Name(rs.getString("name")), rs.getInt("position")), gameId);
    }
}
