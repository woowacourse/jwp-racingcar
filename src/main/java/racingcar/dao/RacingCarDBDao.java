package racingcar.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarsDto;
import racingcar.dto.TryCountDto;
import racingcar.dto.WinnersDto;

@Component
public class RacingCarDBDao implements RacingCarDao{
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public RacingCarDBDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("RACING_INFO")
            .usingGeneratedKeyColumns("id");
    }

    public void insertGame(final RacingCarsDto racingCarsDto, final TryCountDto tryCountDto) {
        final HashMap<String, String> parameter = insertGameInfo(racingCarsDto, tryCountDto);

        final int gameId = insertActor.executeAndReturnKey(parameter).intValue();

        insertMoveLog(racingCarsDto, gameId);
    }

    private HashMap<String, String> insertGameInfo(final RacingCarsDto racingCarsDto, final TryCountDto tryCountDto) {
        final HashMap<String, String> parameter = new HashMap<>();

        parameter.put("winners", concat(racingCarsDto.getWinnerNames()));
        parameter.put("trial_count", String.valueOf(tryCountDto.getTryCount()));
        parameter.put("created_at", LocalDateTime.now().toString());
        return parameter;
    }

    private void insertMoveLog(final RacingCarsDto racingCarsDto, final int gameId) {
        final String sql = "INSERT INTO RACING_CAR (game_id, name, move) values (?,?,?)";

        List<Object[]> batchArgs = new ArrayList<>();
        for (RacingCarDto racingCarDto : racingCarsDto.getRacingCarDtos()) {
            batchArgs.add(new Object[] {gameId, racingCarDto.getName(), racingCarDto.getPosition()});
        }

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    private String concat(final List<String> names) {
        return String.join(",", names);
    }

    public List<WinnersDto> selectWinners() {
        final String sql = "SELECT id, winners FROM RACING_INFO";

        return jdbcTemplate.query(sql,
            (resultSet, rowNum) -> new WinnersDto(
                resultSet.getInt("id"),
                Arrays.asList(resultSet.getString("winners").split(","))));
    }

    public List<RacingCarDto> selectRace(int gameId) {
        final String sql = "SELECT name, move FROM RACING_CAR WHERE game_id = ?";

        return jdbcTemplate.query(sql,
            (resultSet, rowNum) -> new RacingCarDto(
                resultSet.getString("name"),
                resultSet.getInt("move")),
            gameId);
    }
}
