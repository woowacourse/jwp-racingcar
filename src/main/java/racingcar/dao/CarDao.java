package racingcar.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("car")
                .usingGeneratedKeyColumns("car_id");
    }

    public int save(CarEntity carEntity) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(carEntity);
        return insertActor.executeAndReturnKey(sqlParameterSource).intValue();
    }

    public List<CarEntity> findByGameId(int gameId) {
        String sql = "select name, position from car where game_id = ?";
        List<CarEntity> cars = jdbcTemplate.query(sql, (rs, rowNum) -> {
            CarEntity car = new CarEntity();
            car.setName(rs.getString("name"));
            car.setPosition(rs.getInt("position"));
            return car;
        }, gameId);
        return cars;
    }
}
