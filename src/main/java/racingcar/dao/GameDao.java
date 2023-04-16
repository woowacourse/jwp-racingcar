package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public class GameDao {

    private final static String WINNER_DELIMITER = ",";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public GameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id");
    }

    public int save(List<String> winners, int tryCount, LocalTime playTime) {
        String winnersWithJoining = String.join(WINNER_DELIMITER, winners);
        GameEntity game = new GameEntity(winnersWithJoining, tryCount, playTime);
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(game);
        return insertActor.executeAndReturnKey(sqlParameterSource).intValue();
    }

    public List<GameEntity> findAll() {
        String sql = "select game_id, winners from game";
        List<GameEntity> games = jdbcTemplate.query(sql, (rs, rowNum) -> {
            GameEntity game = new GameEntity();
            game.setGameId(rs.getInt("game_id"));
            game.setWinners(rs.getString("winners"));
            return game;
        });
        return games;
    }
}
