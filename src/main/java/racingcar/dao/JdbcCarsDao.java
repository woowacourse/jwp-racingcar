package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;

@Repository
public class JdbcCarsDao implements CarsDao {

    private final RowMapper<CarEntity> actorRowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getLong("play_record_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
    );
    private final ResultSetExtractor<List<List<CarEntity>>> actorResultSetExtractor = resultSet -> {
        final Map<Long, List<CarEntity>> result = new LinkedHashMap<>();
        while (resultSet.next()) {
            final Long id = resultSet.getLong("play_record_id");
            final List<CarEntity> found = result.getOrDefault(id, new ArrayList<>());
            found.add(actorRowMapper.mapRow(resultSet, resultSet.getRow()));
            result.put(id, found);
        }
        return new ArrayList<>(result.values());
    };

    private final JdbcTemplate jdbcTemplate;

    public JdbcCarsDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(final List<CarEntity> cars) {
        jdbcTemplate.batchUpdate("INSERT INTO cars (play_record_id, name, position) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                        ps.setLong(1, cars.get(i).getPlayRecordId());
                        ps.setString(2, cars.get(i).getName());
                        ps.setInt(3, cars.get(i).getPosition());
                    }

                    @Override
                    public int getBatchSize() {
                        return cars.size();
                    }
                });
    }

    @Override
    public List<CarEntity> find(final Long playRecordId) {
        return jdbcTemplate.query(
                "SELECT play_record_id, name, position FROM cars WHERE play_record_id = ?",
                actorRowMapper, playRecordId
        );
    }

    @Override
    public List<List<CarEntity>> findAllCarsOrderByPlayCreatedAtDesc() {
        return jdbcTemplate.query("SELECT play_record_id, name, position FROM cars, play_records "
                        + "WHERE cars.play_record_id = play_records.id "
                        + "ORDER BY play_records.created_at DESC",
                actorResultSetExtractor);
    }
}
