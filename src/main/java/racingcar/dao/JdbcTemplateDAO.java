package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcTemplateDAO implements RacingGameDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveResult(GameResultDto resultDto) {
        String sql = "insert into GAME_RESULT (winners, trial_count) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, resultDto.getWinners());
            preparedStatement.setInt(2, resultDto.getTrialCount());
            return preparedStatement;
        }, keyHolder);

        int id = keyHolder.getKey().intValue();

        savePlayerResult(id, resultDto.getRacingCars());
        return id;
    }

    private void savePlayerResult(int gameId, List<CarDto> carDtoList) {
        String sql = "insert into PLAYER_RESULT (name, position, game_id) values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, carDtoList, carDtoList.size(),
                (PreparedStatement preparedStatement, CarDto carDto) -> {
                    preparedStatement.setString(1, carDto.getName());
                    preparedStatement.setInt(2, carDto.getPosition());
                    preparedStatement.setInt(3, gameId);
                });
    }
}
