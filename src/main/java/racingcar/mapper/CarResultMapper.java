package racingcar.mapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarResultEntity;

import javax.sql.DataSource;

@Repository
public class CarResultMapper {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<CarResultEntity> entityRowMapper = (resultSet, rowNum) -> {
        CarResultEntity carResultEntity = CarResultEntity.of(
                resultSet.getLong("id"),
                resultSet.getLong("play_result_id"),
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return carResultEntity;
    };

    public CarResultMapper(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("car_result")
                .usingGeneratedKeyColumns("id");
    }

    public long save(CarResultEntity carResultEntity) {
        SqlParameterSource source = new BeanPropertySqlParameterSource(carResultEntity);
        return simpleJdbcInsert.executeAndReturnKey(source).longValue();
    }

    public CarResultEntity findById(long id) {
        String sql = "SELECT * FROM car_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }
}
