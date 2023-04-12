package racingcar;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarStatusResponse;

@Repository
public class PlayerInsertDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private WinnerInsertDao winnerInsertDao;

    public void insertPlayer(List<RacingCarStatusResponse> responses, List<String> winnerNames, int gameId) {
        String sql = "INSERT INTO player(name, position) VALUES(?, ?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        for (RacingCarStatusResponse response : responses) {
            String name = response.getName();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, response.getPosition());
                return preparedStatement;
            }, generatedKeyHolder);

            if (winnerNames.contains(name)) {
                int playerId = generatedKeyHolder.getKey().intValue();
                winnerInsertDao.insertWinner(gameId, playerId);
            }
        }
    }
}
