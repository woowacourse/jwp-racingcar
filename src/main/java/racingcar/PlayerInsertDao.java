package racingcar;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarStatusResponse;

@Repository
public class PlayerInsertDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private WinnerInsertDao winnerInsertDao;

    public void insertPlayer(List<RacingCarStatusResponse> responses, List<String> winnerNames, int gameId) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO player(name, position) VALUES(:name, :position)";

        for (RacingCarStatusResponse response : responses) {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(response);
            jdbcTemplate.update(sql, parameterSource, generatedKeyHolder);

            if (winnerNames.contains(response.getName())) {
                int playerId = generatedKeyHolder.getKey().intValue();
                winnerInsertDao.insertWinner(gameId, playerId);
            }
        }
    }
}
