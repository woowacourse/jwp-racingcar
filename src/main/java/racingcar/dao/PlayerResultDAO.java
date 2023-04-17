package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
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

    private final RowMapper<PlayerResultDto> rowMapper = (resultSet, rowNum) -> PlayerResultDto.of(
            resultSet.getString("name"),
            resultSet.getInt("position")
    );


    public void save(int gameId, List<PlayerResultDto> playerResultDtoList) {
        String sql = "insert into PLAYER_RESULT (name, position, game_id) values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, playerResultDtoList, playerResultDtoList.size(),
                (PreparedStatement preparedStatement, PlayerResultDto playerResultDto) -> {
                    preparedStatement.setString(1, playerResultDto.getName());
                    preparedStatement.setInt(2, playerResultDto.getPosition());
                    preparedStatement.setInt(3, gameId);
                });
    }

    public List<PlayerResultDto> findAllByGameId(int gameId) {
        String sql = "select name, position from PLAYER_RESULT WHERE game_id = ?";

        return jdbcTemplate.query(sql, rowMapper, gameId);
    }
}
