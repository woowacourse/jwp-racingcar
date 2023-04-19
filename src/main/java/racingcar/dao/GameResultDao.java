package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.car.GameResultResponseDto;
import racingcar.dto.car.PlayerHistoryDto;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class GameResultDao {

    private final JdbcTemplate jdbcTemplate;

    public GameResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Long> findAllGamesId() {
        String sql = "SELECT id FROM game";
        return jdbcTemplate.queryForList(sql, Long.class);
    }

    public List<PlayerHistoryDto> findPlayerHistoriesByGameId(final Long gameId) {
        String sql = "SELECT name, position, isWinner FROM player WHERE game_id = ?";

        List<PlayerHistoryDto> playerHistories = new ArrayList<>();

        jdbcTemplate.query(sql, preparedStatement -> preparedStatement.setLong(1, gameId), response -> {
            String name = response.getString("name");
            int position = response.getInt("position");
            boolean isWinner = response.getBoolean("isWinner");
            playerHistories.add(PlayerHistoryDto.toDto(name, position, isWinner));
        });

        return playerHistories;
    }

    public GameResultResponseDto saveGame(final Cars cars, final TryCount tryCount) {
        long gameId = saveGameHistory(tryCount);
        return savePlayersStatus(cars, gameId);
    }

    private Long saveGameHistory(final TryCount tryCount) {
        String sql = "INSERT INTO game (trialCount, dateTime) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tryCount.getTryCount());
            preparedStatement.setTimestamp(2, Timestamp.from(Instant.now()));
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private GameResultResponseDto savePlayersStatus(final Cars cars, final Long gameId) {
        String sql = "INSERT INTO player (game_id, name, position, isWinner) VALUES (?, ?, ?, ?)";

        cars.getCars()
                .forEach(racingCar -> {
                    jdbcTemplate.update(sql,
                            gameId,
                            racingCar.getCarName(),
                            racingCar.getDistance(),
                            cars.isWinner(racingCar.getCarName()));
                });

        return GameResultResponseDto.toDto(cars);
    }
}
