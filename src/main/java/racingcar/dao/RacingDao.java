package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarInfo;
import racingcar.vo.Trial;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@Repository
public class RacingDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public RacingDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("car_info");
    }

    public int insert(Trial trial) {
        String sql = "INSERT INTO racing (trialCount) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
                    preparedStatement.setInt(1, trial.getValue());
                    return preparedStatement;
                },
                keyHolder
        );

        return keyHolder.getKey().intValue();
    }

    public void insert(CarInfo carInfo) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(carInfo);
        simpleJdbcInsert.execute(parameterSource);
    }
}
