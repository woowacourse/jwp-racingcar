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
import racingcar.dto.JudgedCarDto;

@Repository
public class CarsDao {

    private final RowMapper<JudgedCarDto> actorRowMapper = (resultSet, rowNum) -> {
        JudgedCarDto car = JudgedCarDto.of(
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getBoolean("is_winner")
        );
        return car;
    };

    private final JdbcTemplate jdbcTemplate;

    public CarsDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long id, final List<JudgedCarDto> cars) {
        jdbcTemplate.batchUpdate("INSERT INTO cars (play_id, name, position, is_winner) VALUES (?, ?, ?, ?)",
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

    public List<JudgedCarDto> find(final long id) {
        return jdbcTemplate.query(
                "SELECT name, position, is_winner FROM cars WHERE play_id = ?",
                actorRowMapper, id
        );
    }

    // TODO 다른 테이블과 조인하는 쿼리를 해당 Dao에서 쓰는 게 맞을까?
    public Map<Long, List<JudgedCarDto>> findAllCarsByPlayId() {
        return jdbcTemplate.query(
                "SELECT play_id, name, position, is_winner FROM cars, play_records "
                        + "WHERE cars.play_id = play_records.id "
                        + "ORDER BY play_records.created_at DESC",
                resultSet -> {
                    final Map<Long, List<JudgedCarDto>> result = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        final long id = resultSet.getLong("play_id");
                        final List<JudgedCarDto> found = result.getOrDefault(id, new ArrayList<>());
                        found.add(actorRowMapper.mapRow(resultSet, resultSet.getRow()));
                        result.put(id, found);
                    }
                    return result;
                });
    }
}
