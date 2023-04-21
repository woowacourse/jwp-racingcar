package racingcar.repository;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
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

    public Game save(final Game game) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(game);
        final long id = insertGame.executeAndReturnKey(params).longValue();
        return new Game(id, game.getTrialCount(), game.getWinners(), game.getCreatedAt());
    }
}
