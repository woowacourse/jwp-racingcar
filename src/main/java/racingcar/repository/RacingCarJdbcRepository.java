package racingcar.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

@Repository
public class RacingCarJdbcRepository implements RacingCarRepository {
    private final JdbcTemplate jdbcTemplate;

    public RacingCarJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveWinners(int gameId, List<String> winners) {
        String sql = "INSERT INTO winner_result (game_id,winner) VALUES(?,?)";
        jdbcTemplate.batchUpdate(sql, winners, winners.size(), (ps, winner) -> {
            ps.setInt(1, gameId);
            ps.setString(2, winner);
        });
    }

    @Override
    public void saveCars(int gameId, List<Car> cars) {
        String sql = "INSERT INTO player_result (game_id,name,position) VALUES(?,?,?)";
        jdbcTemplate.batchUpdate(sql, cars, cars.size(), (ps, car) -> {
            ps.setInt(1, gameId);
            ps.setString(2, car.getName());
            ps.setInt(3, car.getPosition());
        });
    }

    @Override
    public int saveGame(int count) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO game_result (trial_count) VALUES(?)";
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

    @Override
    public List<String> findWinnersByGameId(int gameId) {
        String sql = "SELECT winner FROM winner_result WHERE game_id = ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, gameId), rs -> {
            ArrayList<String> winners = new ArrayList<>();
            while (rs.next()) {
                String winner = rs.getString(1);
                winners.add(winner);
            }
            return winners;
        });
    }

    @Override
    public List<RacingCarDto> findRacingCarsByGameId(int gameId) {
        String sql = "SELECT name, position FROM player_result WHERE game_id = ?";
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

    @Override
    public Map<Integer, List<String>> findWinners() {
        String sql = "SELECT winner_result.* FROM game_result JOIN winner_result ON game_result.id = winner_result.game_id";
        Map<Integer, List<String>> winners = new HashMap<>();
        return jdbcTemplate.query(sql, rs -> {
            while (rs.next()) {
                int gameId = rs.getInt("game_id");
                List<String> winnersByGameId = winners.getOrDefault(gameId, new ArrayList<>());
                winnersByGameId.add(rs.getString("winner"));
                winners.put(gameId, winnersByGameId);
            }
            return winners;
        });
    }

    @Override
    public Map<Integer, List<RacingCarDto>> findRacingCars() {
        String sql = "SELECT player_result.* FROM game_result JOIN player_result ON game_result.id = player_result.game_id";
        Map<Integer, List<RacingCarDto>> playerHistory = new HashMap<>();
        return jdbcTemplate.query(sql, rs -> {
            while (rs.next()) {
                int gameId = rs.getInt("game_id");
                List<RacingCarDto> racingCars = playerHistory.getOrDefault(gameId, new ArrayList<>());
                racingCars.add(new RacingCarDto(rs.getString("name"), rs.getInt("position")));
                playerHistory.put(gameId, racingCars);
            }
            return playerHistory;
        });
    }
}
