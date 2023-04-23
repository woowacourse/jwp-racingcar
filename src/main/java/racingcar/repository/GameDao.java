package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.Game;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GameDao {

    private final SimpleJdbcInsert insertGame;
    private final JdbcTemplate jdbcTemplate;

    public GameDao(final DataSource dataSource) {
        this.insertGame = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Game save(final Game game) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(game);
        final long id = insertGame.executeAndReturnKey(params).longValue();
        return new Game(id, game.getTrialCount(), game.getWinners(), game.getCreatedAt());
    }

    public List<Game> findAll() {
        final String sql = "select * from game";
        return jdbcTemplate.query(sql,
                (resultSet, rowNum) -> new Game(
                        resultSet.getLong("id"),
                        resultSet.getInt("trial_count"),
                        resultSet.getString("winners"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                ));
    }
}
