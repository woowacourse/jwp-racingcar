package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.PlayerResultDto;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlayerResultDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerResultDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CarDto> rowMapper = (resultSet, rowNum) -> CarDto.of(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );


    public void saveAll(PlayerResultDto playerResultDtos) {
        String sql = "insert into PLAYER_RESULT (name, position, game_id) values (?, ?, ?)";
        int gameId = playerResultDtos.getGameId();
        List<CarDto> carDtos = playerResultDtos.getCarDtos();

        jdbcTemplate.batchUpdate(sql, carDtos, carDtos.size(),
                (PreparedStatement preparedStatement, CarDto carDto) -> {
                    preparedStatement.setString(1, carDto.getName());
                    preparedStatement.setInt(2, carDto.getPosition());
                    preparedStatement.setInt(3, gameId);
                });
    }

    public List<CarDto> findAllByGameId(int gameId) {
        String sql = "select name, position from PLAYER_RESULT WHERE game_id = ?";

        return jdbcTemplate.query(sql, rowMapper, gameId);
    }
}
