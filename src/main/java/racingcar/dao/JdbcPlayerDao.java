package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Winner;
import racingcar.dto.PlayResultResponseDto;

import java.util.*;

@Repository
public class JdbcPlayerDao implements PlayerDao {

    private final SimpleJdbcInsert insertPlayerActor;
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Car> carRowMapper = new CarRowMapper();

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.insertPlayerActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("PLAYER")
                .usingGeneratedKeyColumns("player_id");
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(int gameId, List<Car> cars) {
        SqlParameterSource[] batchParams = new SqlParameterSource[cars.size()];
        for (int index = 0; index < cars.size(); index++) {
            Car car = cars.get(index);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("game_id", gameId);
            parameters.put("name", car.getName());
            parameters.put("position", car.getDistance());
            batchParams[index] = new MapSqlParameterSource(parameters);
        }

        insertPlayerActor.executeBatch(batchParams);
    }

    @Override
    public List<Car> find(int gameId) {
        String sql = "SELECT name, position FROM PLAYER WHERE game_id = ?";
        return jdbcTemplate.query(sql, carRowMapper, gameId);
    }

    @Override
    public Map<Integer, PlayResultResponseDto> findAll() {
        String sql = "SELECT position, name, PLAYER.game_id, winners FROM PLAYER JOIN GAME WHERE PLAYER.game_id = GAME.game_id";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
        Map<Integer, PlayResultResponseDto> result = new LinkedHashMap<>();

        while (sqlRowSet.next()) {
            int gameId = sqlRowSet.getInt("game_id");
            String winners = sqlRowSet.getString("winners");
            Winner winner = new Winner(Arrays.asList(winners.split(",")));
            int position = sqlRowSet.getInt("position");
            String name = sqlRowSet.getString("name");
            Car car = new Car(name, position);
            result.putIfAbsent(gameId, new PlayResultResponseDto(winner, new ArrayList<>()));
            result.get(gameId).getRacingCars().add(car);
        }
        return result;
    }
}
