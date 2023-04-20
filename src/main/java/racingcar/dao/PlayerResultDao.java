package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.dto.response.PlayerResultDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlayerResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerResultDto> playerResultDtoRowMapper = (resultSet, rowNum) -> {
        PlayerResultDto playerResultDto = new PlayerResultDto(resultSet.getString("name"), resultSet.getInt("final_position"));
        return playerResultDto;
    };

    public void savePlayerResult(List<PlayerResultSaveDto> playerResults) {
        String query = "INSERT INTO player_result (name, final_position, game_id) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(query,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        PlayerResultSaveDto playerResultSaveDto = playerResults.get(i);
                        ps.setString(1, playerResultSaveDto.getName());
                        ps.setInt(2, playerResultSaveDto.getFinalPosition());
                        ps.setLong(3, playerResultSaveDto.getGameId());
                    }

                    @Override
                    public int getBatchSize() {
                        return playerResults.size();
                    }
                });
    }

    public List<PlayerResultDto> findPlayerResultsByGameId(Long gameId) {
        String query = "SELECT name, final_position FROM player_result WHERE player_result.game_id = ?";
        List<PlayerResultDto> playerResultDtos = jdbcTemplate.query(query, playerResultDtoRowMapper, gameId);
        return playerResultDtos;
    }
}
