package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.ResponseDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarDao {
    private final JdbcTemplate jdbcTemplate;

    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CarDto> carDtoRowMapper = (resultSet, rowNum) -> new CarDto(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public void insert(List<CarDto> cars, long gameId) {
        String sql = "INSERT INTO car(game_id, name, position) VALUES (?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, gameId);
                ps.setString(2, cars.get(i).getName());
                ps.setInt(3, cars.get(i).getPosition());
            }

            @Override
            public int getBatchSize() {
                return cars.size();
            }
        });
    }

    public List<CarDto> selectByGameId(long gameId) {
        String sql = "SELECT * FROM car WHERE game_id = ?";
        return jdbcTemplate.query(sql, carDtoRowMapper, gameId);
    }
}
