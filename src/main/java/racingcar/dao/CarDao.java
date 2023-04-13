package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;

@Repository
public class CarDao {

    private static final int IS_WINNER = 1;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public CarDao(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("car")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<CarDto> carDtoRowMapper = (resultSet, rowNum) -> CarDto.of(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public int insertCar(final CarDto carDto, final int gameId) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("name", carDto.getName());
        parameters.put("position", carDto.getPosition());
        parameters.put("is_winner", 0);
        parameters.put("game_id", gameId);

        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    public void updatePosition(final CarDto carDto, final int gameId) {
        String sql = "UPDATE car SET position = ? WHERE game_id = ? AND name = ?";
        jdbcTemplate.update(sql, carDto.getPosition(), gameId, carDto.getName());
    }

    public void updateWinner(final String name, final int gameId) {
        String sql = "UPDATE car SET is_winner = 1 WHERE game_id = ? AND name = ?";
        jdbcTemplate.update(sql, gameId, name);
    }

    public List<CarDto> findWinners(int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ? AND is_winner = ?";
        return jdbcTemplate.query(sql, carDtoRowMapper, gameId, IS_WINNER);
    }

    public List<CarDto> findCars(int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ?";
        return jdbcTemplate.query(sql, carDtoRowMapper, gameId);
    }

    public CarDto findCar(String name, int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ? AND name = ?";
        return jdbcTemplate.queryForObject(sql, carDtoRowMapper, gameId, name);
    }
}
