package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarDao {
    private final JdbcTemplate jdbcTemplate;

    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertCar(GameResultDto gameResultDto, long gameId) {
        String sql = "INSERT INTO car(game_id, name, position) VALUES (?,?,?)";
        List<CarDto> racingCars = gameResultDto.getRacingCars();
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, gameId);
                ps.setString(2, racingCars.get(i).getName());
                ps.setInt(3, racingCars.get(i).getPosition());
            }

            @Override
            public int getBatchSize() {
                return racingCars.size();
            }
        });
    }
}
