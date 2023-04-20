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
public class CarJdbcDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public CarJdbcDao(final JdbcTemplate jdbcTemplate, final DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("car")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<CarDto> carDtoRowMapper = (resultSet, rowNum) -> CarDto.of(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    @Override
    public int insertCar(final CarDto carDto, final int gameId) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("name", carDto.getName());
        parameters.put("position", carDto.getPosition());
        parameters.put("game_id", gameId);

        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public List<CarDto> findCars(final int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ?";
        return jdbcTemplate.query(sql, carDtoRowMapper, gameId);
    }
}
