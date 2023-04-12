package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.TryCount;
import racingcar.dto.GameResultResponseDto;

@Repository
public class GameResultDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GameResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveGame(final TryCount tryCount, final GameResultResponseDto gameResult) {
        int gameId = saveGameHistory(tryCount);
        savePlayersStatus(gameResult, gameId);
    }

    public int saveGameHistory(final TryCount tryCount) {
        String sql = "INSERT INTO game (trialCount, dateTime) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, tryCount.getTryCount());
            preparedStatement.setTimestamp(2, Timestamp.from(Instant.now()));
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    private void savePlayersStatus(final GameResultResponseDto gameResult, final int gameId) {
        String sql = "INSERT INTO player (game_id, name, position, isWinner) VALUES (?, ?, ?, ?)";

        gameResult.getRacingCars()
                .forEach(racingCar -> {
                    jdbcTemplate.update(sql,
                            gameId,
                            racingCar.getName(),
                            racingCar.getPosition(),
                            gameResult.isWinner(racingCar.getName()));
                });
    }
}
