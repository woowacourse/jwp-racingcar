package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.dto.response.PlayerResultDto;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlayerResultDao {

    private final SimpleJdbcInsert insertPlayerResult;
    private final JdbcTemplate jdbcTemplate;

    public PlayerResultDao(final DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.insertPlayerResult = new SimpleJdbcInsert(dataSource)
                .withTableName("player_result")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerResultDto> playerResultDtoRowMapper = (resultSet, rowNum) -> {
        PlayerResultDto playerResultDto = new PlayerResultDto(resultSet.getString("name"), resultSet.getInt("final_position"));
        return playerResultDto;
    };

    public PlayerResultDto savePlayerResult(final PlayerResultSaveDto playerResultSaveDto) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(playerResultSaveDto);
        insertPlayerResult.execute(params);
        return new PlayerResultDto(playerResultSaveDto.getName(), playerResultSaveDto.getFinalPosition());
    }

    public List<PlayerResultDto> findPlayerResultsByGameId(Long gameId) {
        String query = "SELECT name, final_position FROM player_result WHERE player_result.game_id = ?";
        List<PlayerResultDto> playerResultDtos = jdbcTemplate.query(query, playerResultDtoRowMapper, gameId);
        return playerResultDtos;
    }
}
