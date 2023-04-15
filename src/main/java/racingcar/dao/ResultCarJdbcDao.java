package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.dto.CarDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultCarJdbcDao implements ResultCarDao {
    private final JdbcTemplate jdbcTemplate;

    public ResultCarJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(int gameId, List<CarDto> carDtoList) {
        String sql = "insert into RESULT_CAR (name, position, game_id) values (?, ?, ?)";

        List<Object[]> resultCars = new ArrayList<>();
        for (CarDto dto : carDtoList) {
            resultCars.add(new Object[]{dto.getName(), dto.getPosition(), gameId});
        }

        jdbcTemplate.batchUpdate(sql, resultCars);
    }
}
