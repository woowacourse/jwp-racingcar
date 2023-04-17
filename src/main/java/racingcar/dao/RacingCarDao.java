package racingcar.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingCarsDto;
import racingcar.dto.TryCountDto;

@Component
public class RacingCarDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public RacingCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("PLAY_RESULT")
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
        final String sql = "INSERT INTO MOVE_LOG (game_id, name, move) values (?,?,?)";

        List<Object[]> batchArgs = new ArrayList<>();
        for (RacingCarDto racingCarDto : racingCarsDto.getRacingCarDtos()) {
            batchArgs.add(new Object[] {gameId, racingCarDto.getName(), racingCarDto.getPosition()});
        }

        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    private String concat(final List<String> names) {
        return String.join(",", names);
    }
}
