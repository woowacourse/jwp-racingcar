package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;

@Repository
public class CarDao {

    private final RowMapper<CarDto> actorRowMapper = (resultSet, rowNum) -> {
        CarDto car = CarDto.of(
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getBoolean("is_winner")
        );
        return car;
    };

    private final JdbcTemplate jdbcTemplate;

    public CarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long id, final List<CarDto> cars) {
        jdbcTemplate.batchUpdate("INSERT INTO car (play_result_id, name, position, is_winner) VALUES (?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                        ps.setLong(1, id);
                        ps.setString(2, cars.get(i).getName());
                        ps.setInt(3, cars.get(i).getPosition());
                        ps.setBoolean(4, cars.get(i).isWinner());
                    }

                    @Override
                    public int getBatchSize() {
                        return cars.size();
                    }
                });
    }

    public List<CarDto> find(final long id) {
        return jdbcTemplate.query(
                "SELECT name, position, is_winner FROM car WHERE play_result_id = ?",
                actorRowMapper, id
        );
    }

    public Map<Long, List<CarDto>> findAllCarsById() {
        return jdbcTemplate.query(
                "SELECT play_result_id, name, position, is_winner FROM car, play_result "
                        + "WHERE car.play_result_id = play_result.id "
                        + "ORDER BY play_result.created_at DESC",
                resultSet -> {
                    Map<Long, List<CarDto>> result = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        long id = resultSet.getLong("play_result_id");
                        List<CarDto> found = result.getOrDefault(id, new ArrayList<>());
                        found.add(actorRowMapper.mapRow(resultSet, resultSet.getRow()));
                        result.put(id, found);
                    }
                    return result;
                });
    }
}
