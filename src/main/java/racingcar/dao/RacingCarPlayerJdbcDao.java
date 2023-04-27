package racingcar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Name;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;

@Component
public class RacingCarPlayerJdbcDao implements RacingCarPlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarPlayerJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insertGameLog(final RacingCars racingCars, final int gameId) {
        final String sql = "INSERT INTO RACING_CAR_PLAYER_LOG (game_id, name, move) values (?,?,?)";

        for (RacingCar racingCar : racingCars.getRacingCars()) {
            jdbcTemplate.update(sql, gameId, racingCar.getName(), racingCar.getPosition());
        }
    }

    @Transactional
    public List<RacingCars> findAll() {
        final String sql = "SELECT game_id, name, move FROM RACING_CAR_PLAYER_LOG";

        List<Map<Integer, RacingCar>> log = jdbcTemplate.query(sql, RacingCarPlayerJdbcDao::makeRacingCar);
        return makeRacingCars(log);
    }

    private static Map<Integer, RacingCar> makeRacingCar(ResultSet rs, int rowNum) throws SQLException {
        final int gameId = rs.getInt("game_id");
        final RacingCar racingCar = new RacingCar(
                new Name(rs.getString("name")),
                rs.getInt("move"));

        return Map.of(gameId, racingCar);
    }

    private List<RacingCars> makeRacingCars(final List<Map<Integer, RacingCar>> racingCar) {
        Map<Integer, List<RacingCar>> groupedCar = racingCar.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(Entry::getKey,
                        Collectors.mapping(Entry::getValue, Collectors.toUnmodifiableList())));

        return groupedCar.values().stream().map(RacingCars::new).collect(Collectors.toUnmodifiableList());
    }
}
