package racingcar.repository.h2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;
import racingcar.repository.WinnerRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class H2WinnerRepository implements WinnerRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public H2WinnerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public CarEntity save(CarEntity entity) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("car_id", entity.getId());
        long id = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
        return entity;
    }
}
