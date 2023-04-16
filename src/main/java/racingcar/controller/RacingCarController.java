package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.PlayRequestDto;
import racingcar.view.util.TextParser;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RacingCarController {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarController(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @PostMapping("/plays")
    public ResponseEntity<GameResultDto> play(@RequestBody PlayRequestDto playRequestDto) {
        RacingGame racingGame = createGame(playRequestDto.getNames());
        int count = playRequestDto.getCount();
        racingGame.race(count);

        String winners = String.join(", ", racingGame.getWinnerNames());
        final List<Car> cars = racingGame.getCars();

        final long resultId = saveWinners(count, winners);
        saveCars(resultId, cars);

        final List<CarDto> carsDto = cars.stream().map(CarDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(new GameResultDto(carsDto, winners));
    }

    private static RacingGame createGame(final String rawCarNames) {
        List<String> carNames = TextParser.parseByDelimiter(rawCarNames, ",");
        return RacingGame.of(carNames);
    }

    private long saveWinners(final int count, final String winners) {
        String sql = "INSERT INTO play_result(winners, trial_count) VALUES (?, ?)";
        final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, winners);
            preparedStatement.setInt(2, count);

            return preparedStatement;
        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().longValue();
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
