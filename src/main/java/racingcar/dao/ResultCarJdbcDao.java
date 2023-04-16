package racingcar.dao;

import org.springframework.dao.DataAccessException;
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

    @Override
    public void save(int gameId, List<CarDto> carDtoList) {
        String sql = "insert into RESULT_CAR (name, position, game_id) values (?, ?, ?)";

        List<Object[]> resultCars = new ArrayList<>();
        for (CarDto dto : carDtoList) {
            resultCars.add(new Object[]{dto.getName(), dto.getPosition(), gameId});
        }

        try {
            jdbcTemplate.batchUpdate(sql, resultCars);
        } catch (DataAccessException e) {
            throw new IllegalArgumentException("존재하지 않는 게임입니다.");
        }
    }

    @Override
    public List<CarDto> findByGameId(int gameId) {
        String sql = "select name,position from RESULT_CAR where game_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new CarDto(rs.getString("name"), rs.getInt("position")), gameId);
    }
}
