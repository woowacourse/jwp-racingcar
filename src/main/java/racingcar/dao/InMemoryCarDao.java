package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;
import racingcar.model.Car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InMemoryCarDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public InMemoryCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(int gameId, List<Car> cars) {
        String sql = "insert into CAR_RESULT (play_result_id, name, position) values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, gameId);
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
    public List<CarEntity> findAllById(int gameId) {
        String sql = "select * from CAR_RESULT where play_result_id = ?";

        return jdbcTemplate.query(sql, (result, id) ->
                        new CarEntity(result.getInt("play_result_id"),
                                result.getString("name"),
                                result.getInt("position"))
                , gameId);
    }
}
