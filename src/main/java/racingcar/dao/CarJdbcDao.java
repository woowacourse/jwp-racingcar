package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;

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

    private final RowMapper<CarEntity> carDtoRowMapper = (resultSet, rowNum) -> CarEntity.of(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    @Override
    public int insertCar(final CarEntity carEntity, final int gameId) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put("name", carEntity.getName());
        parameters.put("position", carEntity.getPosition());
        parameters.put("game_id", gameId);

        return insertActor.executeAndReturnKey(parameters).intValue();
    }

    @Override
    public List<CarEntity> findCars(final int gameId) {
        String sql = "SELECT name, position FROM car WHERE game_id = ?";
        return jdbcTemplate.query(sql, carDtoRowMapper, gameId);
    }
}
