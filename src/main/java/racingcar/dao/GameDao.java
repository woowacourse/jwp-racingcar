package racingcar.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.request.GameSaveDto;
import racingcar.entity.Game;

import javax.sql.DataSource;

@Repository
public class GameDao {

    private final SimpleJdbcInsert insertGame;

    public GameDao(final DataSource dataSource) {
        this.insertGame = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    public Game createGame(final GameSaveDto gameSaveDto) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(gameSaveDto);
        final long id = insertGame.executeAndReturnKey(params).longValue();
        return new Game(id, gameSaveDto);
    }
}
