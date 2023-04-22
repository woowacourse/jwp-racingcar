package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.domain.Car;
import racingcar.dto.response.RacingCarResponseDto;

@Repository
public class RacingCarDao {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_car")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(final Long gameId, final Car car) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("game_id", gameId);
        parameters.put("name", car.getName());
        parameters.put("position", car.getPosition());
        return insertActor.executeAndReturnKey(parameters).longValue();
    }

    public List<RacingCarResponseDto> findAllCars() {
        String sql = "select * from racing_car";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> {
                    RacingCarResponseDto racingCarResponseDto = new RacingCarResponseDto(
                            resultSet.getString("name"),
                            resultSet.getInt("position"),
                            resultSet.getLong("game_id"));
                    return racingCarResponseDto;
                });
    }

}
