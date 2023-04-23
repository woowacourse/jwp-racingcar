package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarEntity;

import java.util.List;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<CarEntity> rowMapper = (rs, rowNum) ->
            new CarEntity(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("position"),
                    rs.getLong("race_result_id"),
                    rs.getBoolean("winner"),
                    rs.getTimestamp("created_at").toLocalDateTime()
            );

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("CAR")
                        .usingGeneratedKeyColumns("id");
    }

    public void save(final List<CarEntity> carEntities) {
        simpleJdbcInsert.executeBatch(SqlParameterSourceUtils.createBatch(carEntities));
    }

    public List<CarEntity> findAll() {
        final String sql = "select * from CAR";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
