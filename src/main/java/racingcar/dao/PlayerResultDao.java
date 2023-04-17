package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.service.PlayerResult;

@Repository
public class PlayerResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertPlayerResult(final PlayerResult playerResult) {
        String sql = "INSERT INTO PLAYER_RESULT (play_result_id, name, position) VALUES(?,?,?)";

        return jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, playerResult.getPlayResultId());
            preparedStatement.setString(2, playerResult.getName());
            preparedStatement.setInt(3, playerResult.getPosition());
            return preparedStatement;
        }, new GeneratedKeyHolder());
    }

    public List<PlayerResult> selectPlayerResult(final int playResultId) {
        String sql = "select * from player_result where PLAY_RESULT_ID = ?";

        return jdbcTemplate.query(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, playResultId);
            return preparedStatement;
        }, (rs, rowNum) -> {
            int findPlayResultId = rs.getInt("play_result_id");
            String name = rs.getString("name");
            int position = rs.getInt("position");
            return new PlayerResult(findPlayResultId, name, position);
        });
    }

//    public PlayerResultDao(final DataSource dataSource, final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.insertActor = new SimpleJdbcInsert(dataSource)
//                .withTableName("PLAYER_RESULT")
//                .usingGeneratedKeyColumns("id");
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }
//
//    public List<PlayerResult> selectPlayerResultByPlayResultId(final int playResultId) {
//        String sql = "select play_result_id, name, position from player_result where PLAY_RESULT_ID = :play_result_id";
//        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource("play_result_id", playResultId);
//        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, actorRowMapper);
//    }
}
