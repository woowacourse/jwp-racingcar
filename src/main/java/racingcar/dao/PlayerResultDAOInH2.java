package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.PlayerResultEntity;
import racingcar.dto.CarDto;
import racingcar.dto.PlayerResultDto;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlayerResultDAOInH2 implements PlayerResultDAO{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerResultDAOInH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerResultEntity> rowMapper = (resultSet, rowNum) -> PlayerResultEntity.of(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("position"),
            resultSet.getInt("game_id")
    );

    @Override
    public void saveAll(PlayerResultDto playerResultDto) {
        String sql = "insert into PLAYER_RESULT (name, position, game_id) values (?, ?, ?)";
        int gameId = playerResultDto.getGameId();
        List<CarDto> carDtos = playerResultDto.getCarDtos();

        jdbcTemplate.batchUpdate(sql, carDtos, carDtos.size(),
                (PreparedStatement preparedStatement, CarDto carDto) -> {
                    preparedStatement.setString(1, carDto.getName());
                    preparedStatement.setInt(2, carDto.getPosition());
                    preparedStatement.setInt(3, gameId);
                });
    }

    public List<PlayerResultEntity> findAll(){
        String sql = "select * from PLAYER_RESULT";

        return jdbcTemplate.query(sql, rowMapper);
    }
}
