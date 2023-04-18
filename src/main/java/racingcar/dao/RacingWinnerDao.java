package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.entity.RacingWinnerEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class RacingWinnerDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingWinnerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAll(final List<RacingWinnerEntity> racingWinners) {
        final String sql = "INSERT INTO RACING_WINNER (game_id, car_id) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, racingWinners.get(i).getGameId());
                ps.setLong(2, racingWinners.get(i).getCarId());
            }

            @Override
            public int getBatchSize() {
                return racingWinners.size();
            }
        });
    }
}
