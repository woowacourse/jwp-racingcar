package racingcar.repository;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Cars;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PlayerJdbcRepository implements PlayerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayerJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(final Cars cars, final int racingGameId) {
        final String sql = "INSERT INTO PLAYER(name, position, racing_game_id) VALUES(?, ?, ?)";
        final int[] updatedCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                ps.setString(1, cars.getRacingCars().get(i).getName().getValue());
                ps.setInt(2, cars.getRacingCars().get(i).getPosition().getValue());
                ps.setInt(3, racingGameId);
            }

            @Override
            public int getBatchSize() {
                return cars.getRacingCars().size();
            }
        });

        return updatedCounts.length == cars.getRacingCars().size();
    }
}
