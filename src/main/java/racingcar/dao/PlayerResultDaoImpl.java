package racingcar.dao;

import java.util.List;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerResultDaoImpl implements PlayerResultDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PlayerResultDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void insertPlayerResult(final PlayerResult playerResult) {
        String sql = "INSERT INTO PLAYER_RESULT(play_result_id, name, position) VALUES(:playResultId, :name, :position)";

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(playerResult);

        namedParameterJdbcTemplate.update(sql, parameterSource, new GeneratedKeyHolder());
    }

    @Override
    public List<PlayerResult> selectPlayerResult(final int playResultId) {
        String sql = "select * from player_result where PLAY_RESULT_ID = :playResultId";
        SqlParameterSource parameterSource = new MapSqlParameterSource("playResultId", playResultId);

        return namedParameterJdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> {
            int findPlayResultId = rs.getInt("play_result_id");
            String name = rs.getString("name");
            int position = rs.getInt("position");
            return new PlayerResult(findPlayResultId, name, position);
        });
    }

}
