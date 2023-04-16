package racingcar.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;

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

    public int save(CarDto carDto) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(carDto);
        return insertActor.executeAndReturnKey(sqlParameterSource).intValue();
    }
}
