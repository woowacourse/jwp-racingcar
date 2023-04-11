package racingcar.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;

@Repository
public class RacingCarRepository {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveWinner(int gameId, List<String> winners) {
        String sql = "INSERT INTO WINNER_RESULT (GAME_ID,WINNER) VALUES(?,?)";
        jdbcTemplate.batchUpdate(sql, winners, winners.size(), (ps, winner) -> {
            ps.setInt(1, gameId);
            ps.setString(2, winner);
        });
    }

    public void saveCars(int gameId, RacingCars racingCars) {
        String sql = "INSERT INTO PLAYER_RESULT (GAME_ID,NAME,POSITION) VALUES(?,?,?)";
        List<Car> cars = racingCars.getCars();
        jdbcTemplate.batchUpdate(sql, cars, cars.size(), (ps, car) -> {
            ps.setInt(1, gameId);
            ps.setString(2, car.getName());
            ps.setInt(3, car.getPosition());
        });
    }

    public int saveGame(int count) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO GAME_RESULT (TRIAL_COUNT) VALUES(?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, count);
            return ps;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        return (int) keys.get("ID");
    }
}
