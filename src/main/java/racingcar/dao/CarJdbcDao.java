package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;

@Repository
public class CarJdbcDao implements CarDao {
    private final JdbcTemplate jdbcTemplate;

    public CarJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(Long gameId, List<CarDto> racingCars) {
        String sql = "INSERT INTO CAR (name, position, is_win, racing_game_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarDto carDto = racingCars.get(i);
                ps.setString(1, carDto.getName());
                ps.setInt(2, carDto.getPosition());
                ps.setBoolean(3, carDto.isWin());
                ps.setLong(4, gameId);
            }

            @Override
            public int getBatchSize() {
                return racingCars.size();
            }
        });
    }
}
