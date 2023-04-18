package racingcar.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalTime;
import java.util.List;

@Repository
public class GameDao {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert insertActor;

    public GameDao(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id");
    }

    public int save(List<String> winners, int tryCount, LocalTime playTime) {
        String winnersWithJoining = String.join(GameEntity.WINNER_DELIMITER, winners);
        GameEntity game = new GameEntity(winnersWithJoining, tryCount, playTime);
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(game);
        return insertActor.executeAndReturnKey(sqlParameterSource).intValue();
    }

    public List<GameEntity> findAll() {
        String sql = "select game_id, winners from game";
        return template.query(sql, rowMapper());
    }

    private static RowMapper<GameEntity> rowMapper() {
        return (rs, rowNum) -> {
            int gameId = rs.getInt("game_id");
            String winners = rs.getString("winners");
            return new GameEntity(gameId, winners);
        };
    }
}
