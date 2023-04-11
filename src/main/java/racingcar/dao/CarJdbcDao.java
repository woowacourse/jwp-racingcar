package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;

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
    public void saveAll(Long racingGameId, List<CarDto> racingCars) {
        racingCars.forEach(carDto -> save(racingGameId, carDto));
    }
}
