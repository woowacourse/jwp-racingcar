package racingcar.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import racingcar.domain.CarGroup;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlayerRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(final CarGroup carGroup, final int racingGameId) {
        final String sql = "INSERT INTO PLAYER(name, position, racing_game_id) VALUES(?, ?, ?)";
        final int[] updatedCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                ps.setString(1, carGroup.getCars().get(i).getName().getName());
                ps.setInt(2, carGroup.getCars().get(i).getPosition().getPosition());
                ps.setInt(3, racingGameId);
            }

            @Override
            public int getBatchSize() {
                return carGroup.getCars().size();
            }
        });

        return updatedCounts.length == carGroup.getCars().size();
    }
}
