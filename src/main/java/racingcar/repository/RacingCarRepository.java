package racingcar.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

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

    public void saveCars(int gameId, List<Car> cars) {
        String sql = "INSERT INTO PLAYER_RESULT (GAME_ID,NAME,POSITION) VALUES(?,?,?)";
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
        if (keys == null) {
            throw new IllegalArgumentException("[ERROR] 게임이 정상적으로 저장되지 못했습니다.");
        }
        return (int) keys.get("ID");
    }

    public List<String> findWinners(int gameId) {
        String sql = "SELECT winner FROM WINNER_RESULT WHERE game_id = ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, gameId), rs -> {
            ArrayList<String> winners = new ArrayList<>();
            while (rs.next()) {
                String winner = rs.getString(1);
                winners.add(winner);
            }
            return winners;
        });
    }

    public List<RacingCarDto> findRacingCars(int gameId) {
        String sql = "SELECT name, position FROM PLAYER_RESULT WHERE game_id = ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, gameId), rs -> {
            ArrayList<RacingCarDto> racingCars = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString(1);
                int position = rs.getInt(2);
                racingCars.add(new RacingCarDto(name, position));
            }
            return racingCars;
        });
    }
}
