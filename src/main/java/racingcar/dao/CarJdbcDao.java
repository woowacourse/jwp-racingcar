package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;

import java.util.List;

@Repository
public class CarJdbcDao implements CarDao {
    private final JdbcTemplate jdbcTemplate;

    public CarJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Long gameId, CarDto carDto) {
        String sql = "INSERT INTO CAR (name, position, racing_game_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, carDto.getName(), carDto.getPosition(), gameId);
    }

    @Override
    public void saveAll(Long gameId, List<CarDto> racingCars) {
        racingCars.forEach(carDto -> save(gameId, carDto));
    }

    @Override
    public List<CarDto> findCarsByGameId(Long gameId) {
        String sql = "SELECT name, position FROM car WHERE racing_game_id = ?";
        return jdbcTemplate.query(sql,
                (resultSet, rowNum) -> CarDto.of(
                        resultSet.getString("name"),
                        resultSet.getInt("position")),
                gameId);
    }
}
