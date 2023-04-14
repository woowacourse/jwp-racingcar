package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResponse;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class RacingGameJdbcDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(String winners, int trialCount) {
        String sql = "INSERT into RACING_GAME (winners, trial_count) values (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, winners);
            preparedStatement.setInt(2, trialCount);
            return preparedStatement;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public List<RacingGameResponse> loadHistories(CarDao carDao) {
        String sqlForHistories = "SELECT id, winners FROM racing_game";

        return jdbcTemplate.query(
                sqlForHistories,
                (resultSet, rowNum) -> {
                    Long gameId = resultSet.getLong("id");
                    String winners = resultSet.getString("winners");
                    List<CarDto> racingCars = carDao.findCarsByGameId(gameId);

                    return new RacingGameResponse(winners, racingCars);

                });
    }
}
