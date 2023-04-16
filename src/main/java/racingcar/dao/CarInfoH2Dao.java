package racingcar.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.dto.CarSavingDto;

import javax.sql.DataSource;

@Component
public class CarInfoH2Dao implements CarInfoDao {
    private final SimpleJdbcInsert carInfoSimpleJdbcInsert;
    public CarInfoH2Dao(DataSource dataSource) {
        this.carInfoSimpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("car_info")
                .usingGeneratedKeyColumns("id");
    }
    public int saveCar(CarSavingDto carSavingDto) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(carSavingDto);
        return carInfoSimpleJdbcInsert
                .executeAndReturnKey(parameterSource)
                .intValue();
    }
}
