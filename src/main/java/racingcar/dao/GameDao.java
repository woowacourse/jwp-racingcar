package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.response.GameWinnerDto;
import racingcar.entity.Game;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GameDao {

    private final SimpleJdbcInsert insertGame;
    private final JdbcTemplate jdbcTemplate;

    public GameDao(final DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.insertGame = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<GameWinnerDto> gameResultDtoRowMapper = (resultSet, rowNum) -> {
        GameWinnerDto gameResultDto = new GameWinnerDto(resultSet.getLong("id"), resultSet.getString("winners"));
        return gameResultDto;
    };


    public Game createGame(final GameSaveDto gameSaveDto) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(gameSaveDto);
        final long id = insertGame.executeAndReturnKey(params).longValue();
        return new Game(id, gameSaveDto);
    }

    public List<GameWinnerDto> findAllGames() {
        String query = "SELECT id, winners FROM game";
        return jdbcTemplate.query(query, gameResultDtoRowMapper);
    }
}
