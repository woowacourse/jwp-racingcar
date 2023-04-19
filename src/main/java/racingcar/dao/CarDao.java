package racingcar.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;

import java.util.List;

@Repository
public class CarDao {
    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

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

    public int[] saveAll(List<CarEntity> carEntities) {
        SqlParameterSource[] sqlParameterSource = SqlParameterSourceUtils.createBatch(carEntities);
        return insertActor.executeBatch(sqlParameterSource);
    }
}
