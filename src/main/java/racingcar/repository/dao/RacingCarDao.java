package racingcar.repository.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.service.dto.RacingCarDto;

@Repository
public class RacingCarDao {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final Long gameId, final List<RacingCarDto> cars) {
        final String sql = "insert into RACING_CAR (player_name, player_position, game_id) values (?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                ps.setString(1, cars.get(i).getName());
                ps.setInt(2, cars.get(i).getPosition());
                ps.setLong(3, gameId);
            }

            @Override
            public int getBatchSize() {
                return cars.size();
            }
        });
    }
}
