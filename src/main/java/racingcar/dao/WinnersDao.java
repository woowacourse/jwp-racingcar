package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Winner;

import java.util.List;

@Repository
public class WinnersDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WinnersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Winner> getWinnerNamesByGameId(int gameId) {
        final String sqlForWinnersByGameId = "SELECT name " +
                "FROM WINNERS w " +
                "INNER JOIN racing_game g " +
                "ON w.racing_game_id = g.id " +
                "INNER JOIN racing_car c " +
                "ON w.racing_car_id = c.id " +
                "WHERE w.racing_game_id = ?";
        List<Winner> winners = jdbcTemplate.query(sqlForWinnersByGameId, ObjectMapper.getWinnersEntityMapper(), gameId);
        System.out.println(winners);
        return winners;
    }

    public void saveWinners(int gameId, int carId) {
        final String sqlForSaveWinners = "INSERT INTO WINNERS(racing_game_id, racing_car_id) VALUES(?, ?)";
        jdbcTemplate.update(sqlForSaveWinners, gameId, carId);
    }

}
