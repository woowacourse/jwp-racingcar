package racingcar.domain.dao;

import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.domain.dao.entity.CarEntity;

@Component
public class H2CarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public H2CarDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveAll(final Long raceResultId, final List<CarEntity> carEntities) {
        final String query = "INSERT INTO car (name, position, race_result_id) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(query, carEntities, carEntities.size(),
            (PreparedStatement ps, CarEntity carEntity) -> {
                ps.setString(1, carEntity.getName());
                ps.setLong(2, carEntity.getPosition());
                ps.setLong(3, raceResultId);
            });
    }

    @Override
    public List<CarEntity> findAll(final Long resultId) {
        final String query = "SELECT * FROM car WHERE race_result_id = ?";
        return jdbcTemplate.query(query, (result, count) ->
            new CarEntity(result.getLong("car_id"), result.getString("name"),
                result.getInt("position")), resultId);
    }
}
