package racingcar.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.entity.CarInfo;

import javax.sql.DataSource;

@Component
public class CarInfoH2Repository implements CarInfoRepository {
    private final SimpleJdbcInsert carInfoSimpleJdbcInsert;
    public CarInfoH2Repository(DataSource dataSource) {
        this.carInfoSimpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("car_info")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public int saveCar(CarInfo carInfo) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(carInfo);
        return carInfoSimpleJdbcInsert
                .executeAndReturnKey(parameterSource)
                .intValue();
    }
}
