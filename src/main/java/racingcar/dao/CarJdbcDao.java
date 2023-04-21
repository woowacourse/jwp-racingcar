package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.CarEntity;

@Component
public class CarJdbcDao implements CarDao {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public CarJdbcDao(JdbcTemplate jdbcTemplate) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("car")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<CarEntity> racingCars) {
        BeanPropertySqlParameterSource[] parameterSources = racingCars.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);
        simpleJdbcInsert.executeBatch(parameterSources);
    }

    @Override
    public List<CarEntity> findByRacingGameId(Long racingGameId) {
        return jdbcTemplate.query("SELECT * FROM car WHERE racing_game_id = " + racingGameId,
                (resultSet, rowNum) -> new CarEntity(
                        resultSet.getString("name"),
                        resultSet.getInt("position"),
                        resultSet.getBoolean("winner"),
                        racingGameId
                ));
    }
}
