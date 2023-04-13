package racingcar.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.view.util.TextParser;

@RestController
public class RacingCarController {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public RacingCarController(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id")
                .usingColumns("trial_count", "winners");
    }

    @Transactional
    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        RacingGame racingGame = createGame(playRequestDto.getNames());
        int count = playRequestDto.getCount();

        race(count, racingGame);
        Number savedId = insertActor.executeAndReturnKey(Map.of("trial_count", count));

        List<Car> cars = racingGame.getCars();
        String winners = String.join(", ", racingGame.getWinnerNames());
        saveResult(savedId, cars, winners);

        return ResponseEntity.ok(new GameResultDto(cars, winners));
    }

    private static RacingGame createGame(final String rawCarNames) {
        List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }

    private void race(final int count, final RacingGame racingGame) {
        for (int i = 0; i < count; i++) {
            racingGame.race();
        }
    }

    private void saveResult(final Number savedId, final List<Car> cars, final String winners) {
        saveCars(savedId, cars);
        jdbcTemplate.update("UPDATE play_result SET winners = ? WHERE id = " + savedId, winners);
    }

    private void saveCars(final Number savedId, final List<Car> cars) {
        jdbcTemplate.batchUpdate("INSERT INTO car (play_result_id, name, position) VALUES (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                        ps.setLong(1, savedId.longValue());
                        ps.setString(2, cars.get(i).getName());
                        ps.setInt(3, cars.get(i).getPosition());
                    }

                    @Override
                    public int getBatchSize() {
                        return cars.size();
                    }
                });
    }
}
