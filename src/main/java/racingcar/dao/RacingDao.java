package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarSavingDto;
import racingcar.vo.Trial;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class RacingDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert carInfoSimpleJdbcInsert;

    public RacingDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.carInfoSimpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("car_info");
    }

    public int saveRacing(Trial trial) {
        String sql = "INSERT INTO racing (trialCount) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(1, trial.getValue());
                    return preparedStatement;
                },
                keyHolder
        );

        Number key = keyHolder.getKey();
        if (key != null) {
            return key.intValue();
        }

        throw new IllegalStateException("레이싱 정보를 저장하고 키를 가져오지 못했습니다.");
    }

    public int saveCar(CarSavingDto carSavingDto) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(carSavingDto);
        return carInfoSimpleJdbcInsert
                .executeAndReturnKey(parameterSource)
                .intValue();
    }
}
