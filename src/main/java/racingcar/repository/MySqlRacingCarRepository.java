package racingcar.repository;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingResultResponse;
import racingcar.utils.RacingCarDtoMapper;

@Repository
public class MySqlRacingCarRepository implements RacingCarRepository {
    private static final String RACING_CARS_RESULT_DELIMITER = ",";
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
    public String findWinners(int gameId) {
        String sql = "SELECT GROUP_CONCAT(winner) FROM WINNER_RESULT WHERE game_id = ?";
        return jdbcTemplate.query(sql, ps -> ps.setInt(1, gameId), rs -> {
            rs.next();
            return rs.getString(1);
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
    public List<RacingResultResponse> findAllGameResults() {
        String sql = "SELECT GROUP_CONCAT(DISTINCT CONCAT(PR.name,':',PR.position)), GROUP_CONCAT(DISTINCT WR.winner) "
                + "FROM GAME_RESULT AS GR "
                + "JOIN PLAYER_RESULT PR ON GR.id = PR.game_id "
                + "JOIN WINNER_RESULT WR ON GR.id = WR.game_id "
                + "GROUP BY GR.id";
        return jdbcTemplate.query(sql, rs -> {
            ArrayList<RacingResultResponse> results = new ArrayList<>();
            while (rs.next()) {
                String racingCarsResults = rs.getString(1);
                String winners = rs.getString(2);
                results.add(new RacingResultResponse(winners, mapToRacingCarDtos(racingCarsResults)));
            }
            return results;
        });
    }

    private List<RacingCarDto> mapToRacingCarDtos(String racingCarsResults) {
        return Arrays.stream(racingCarsResults.split(RACING_CARS_RESULT_DELIMITER))
                .map(RacingCarDtoMapper::fromString)
                .collect(toList());
    }
}
