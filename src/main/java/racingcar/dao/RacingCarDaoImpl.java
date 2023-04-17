package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RacingCarDaoImpl implements RacingCarDao {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long saveWinners(final int count, final String winners) {
        String sql = "INSERT INTO play_result(winners, trial_count) VALUES (?, ?)";
        final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, winners);
            preparedStatement.setInt(2, count);

            return preparedStatement;
        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().longValue();
    }

    @Override
    public void saveCars(final Number resultId, final List<Car> cars) {
        jdbcTemplate.batchUpdate("INSERT INTO car (play_result_id, name, position) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                        ps.setLong(1, resultId.longValue());
                        ps.setString(2, cars.get(i).getName());
                        ps.setInt(3, cars.get(i).getPosition());
                    }

                    @Override
                    public int getBatchSize() {
                        return cars.size();
                    }
                });
    }
}
