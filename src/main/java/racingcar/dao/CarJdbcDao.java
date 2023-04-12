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
        String sql = "INSERT INTO CAR (name, position, is_win, racing_game_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, carDto.getName(), carDto.getPosition(), carDto.isWin(), gameId);
    }

    @Override
    public void saveAll(Long gameId, List<CarDto> racingCars) {
        racingCars.forEach(carDto -> save(gameId, carDto));
    }

    @Override
    public List<CarDto> findByGameId(Long gameId) {
        String sql = "SELECT name, position, is_win FROM car WHERE racing_game_id = ?";
        return jdbcTemplate.query(sql,
                (resultSet, rowNum) -> new CarDto(
                        resultSet.getString("name"),
                        resultSet.getInt("position"),
                        resultSet.getBoolean("is_win")
        ), gameId);
    }
}
