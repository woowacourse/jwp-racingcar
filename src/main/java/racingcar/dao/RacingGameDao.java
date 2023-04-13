package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;

import java.sql.PreparedStatement;

@Repository
public final class RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final GameResultDto gameResultDto, final int trialCount) {

        final String sqlToInsertGameResult = "INSERT INTO GAME_RESULT (trial_count, winners) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlToInsertGameResult, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            preparedStatement.setString(2, gameResultDto.getWinners());
            return preparedStatement;
        }, keyHolder);

        for (CarDto carDto : gameResultDto.getRacingCars()) {
            String sqlToInsertPlayerResult = "INSERT INTO PLAYER_RESULT (name, position, game_result_id) values (?, ?, ?)";
            jdbcTemplate.update(sqlToInsertPlayerResult, carDto.getName(), Integer.parseInt(carDto.getPosition()), keyHolder.getKey());
        }
    }
}
