package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.domain.entity.CarInfo;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CarInfoH2Repository implements CarInfoRepository {
    private final SimpleJdbcInsert carInfoSimpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public CarInfoH2Repository(DataSource dataSource) {
        this.carInfoSimpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("car_info")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveCar(CarInfo carInfo) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(carInfo);
        return carInfoSimpleJdbcInsert
                .executeAndReturnKey(parameterSource)
                .intValue();
    }

    @Override
    public List<CarInfo> findAllByRaceId(int raceId) {
        return jdbcTemplate.query("SELECT * FROM car_info WHERE racing_id = ?", (rs, rowNum) -> new CarInfo(
                rs.getInt("id"),
                rs.getInt("racing_id"),
                rs.getString("name"),
                rs.getInt("position"),
                rs.getBoolean("is_winner")
        ), raceId);
    }
}
