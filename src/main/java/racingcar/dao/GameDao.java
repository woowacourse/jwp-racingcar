package racingcar.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;

@Component
public class GameDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    @Autowired
    public GameDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trialCount")
                .usingGeneratedKeyColumns("id");
    }

    public void saveGame(int trialCount, ResultDto resultDto) {
        long gameId = insertGame(trialCount);
        insertCar(resultDto, gameId);
        insertWinner(resultDto, gameId);
    }

    private long insertGame(int trialCount) {
        Map<String, Object> map = new HashMap<>();
        map.put("trialCount", trialCount);
        return insertActor.executeAndReturnKey(map).longValue();
    }

    private void insertCar(ResultDto resultDto, long gameId) {
        String sql = "INSERT INTO car(g_id, name, position) VALUES (?,?,?)";
        List<CarDto> racingCars = resultDto.getRacingCars();
        List<Object[]> carsInfo = getCarsInfo(gameId, racingCars);
        jdbcTemplate.batchUpdate(sql, carsInfo);
    }

    private List<Object[]> getCarsInfo(long gameId, List<CarDto> racingCars) {
        return racingCars.stream()
                .map(carDto -> {
                    Object[] objects = new Object[3];
                    objects[0] = gameId;
                    objects[1] = carDto.getName();
                    objects[2] = carDto.getPosition();
                    return objects;
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private void insertWinner(ResultDto resultDto, long gameId) {
        String sql = "INSERT INTO winner (g_id,winner) VALUES (?,?)";
        String[] winners = resultDto.getWinners().split(", ");
        jdbcTemplate.batchUpdate(sql, getWinnerNames(gameId, winners));
    }

    private List<Object[]> getWinnerNames(long gameId, String[] winners) {
        return Arrays.stream(winners)
                .map(winner -> {
                    Object[] winnerName = new Object[2];
                    winnerName[0] = gameId;
                    winnerName[1] = winner;
                    return winnerName;
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
