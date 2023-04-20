package racingcar.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;

@Repository
public class MySqlRacingCarRepository implements RacingCarRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public MySqlRacingCarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("GAME_RESULT")
                .usingGeneratedKeyColumns("id")
                .usingColumns("trial_count");
    }

    @Override
    public void saveWinner(int gameId, List<String> winners) {
        String sql = "INSERT INTO WINNER_RESULT (game_id,winner) VALUES(?,?)";
        jdbcTemplate.batchUpdate(sql, winners, winners.size(), (ps, winner) -> {
            ps.setInt(1, gameId);
            ps.setString(2, winner);
        });
    }

    @Override
    public void saveCars(int gameId, List<Car> cars) {
        String sql = "INSERT INTO PLAYER_RESULT (game_id,name,position) VALUES(?,?,?)";
        jdbcTemplate.batchUpdate(sql, cars, cars.size(), (ps, car) -> {
            ps.setInt(1, gameId);
            ps.setString(2, car.getName());
            ps.setInt(3, car.getPosition());
        });
    }

    @Override
    public int saveGame(int count) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("trial_count", count);
        return simpleJdbcInsert.executeAndReturnKey(parameterSource)
                .intValue();
    }

    @Override
    public List<String> findWinners(int gameId) {
        String sql = "SELECT winner FROM WINNER_RESULT WHERE game_id = ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, gameId), rs -> {
            List<String> winners = new ArrayList<>();
            while (rs.next()) {
                winners.add(rs.getString(1));
            }
            return winners;
        });
    }

    @Override
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

    @Override
    public Map<Integer, List<RacingCarDto>> findAllRacingCars() {
        String sql = "SELECT GR.id, PR.name, PR.position FROM PLAYER_RESULT as PR INNER JOIN GAME_RESULT GR on GR.id = PR.game_id";
        return jdbcTemplate.query(sql, rs -> {
            Map<Integer, List<RacingCarDto>> result = new HashMap<>();
            while (rs.next()) {
                Integer gameId = rs.getInt(1);
                List<RacingCarDto> racingCarDtos = result.computeIfAbsent(gameId, ignore -> new ArrayList<>());
                racingCarDtos.add(new RacingCarDto(rs.getString(2), rs.getInt(3)));
            }
            return result;
        });
    }

    @Override
    public Map<Integer, List<String>> findAllWinners() {
        String sql = "SELECT GR.id, WR.winner FROM WINNER_RESULT as WR INNER JOIN GAME_RESULT GR on GR.id = WR.game_id";
        return jdbcTemplate.query(sql, rs -> {
            Map<Integer, List<String>> result = new HashMap<>();
            while (rs.next()) {
                Integer gameId = rs.getInt(1);
                List<String> winners = result.computeIfAbsent(gameId, ignore -> new ArrayList<>());
                winners.add(rs.getString(2));
            }
            return result;
        });
    }
}
