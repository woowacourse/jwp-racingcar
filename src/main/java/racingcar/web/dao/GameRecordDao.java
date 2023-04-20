package racingcar.web.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.web.controller.dto.GameResultDto;
import racingcar.web.controller.dto.RacingCarDto;

import java.util.List;

@Repository
public class GameRecordDao {

    private final JdbcTemplate jdbcTemplate;

    public GameRecordDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GameResultDto> findGameRecord() {
        List<GameResultDto> gameRecord = this.jdbcTemplate.query(
                "select ID, WINNERS FROM RESULTS",
                (resultSet, rowNum) -> {
                    GameResultDto gameResultDto = new GameResultDto(resultSet.getString("WINNERS"), findRacingCarsDtoById(resultSet.getInt("ID")));
                    return gameResultDto;
                });
        return gameRecord;
    }

    public List<RacingCarDto> findRacingCarsDtoById(int id) {
        String sql = "SELECT * FROM RACING_CARS WHERE RESULT_ID = ?";
        return jdbcTemplate.query(sql, racingCarRowMapper(), id);
    }

    private RowMapper<RacingCarDto> racingCarRowMapper() {
        return (rs, rowNum) -> {
            RacingCarDto racingCarDto = new RacingCarDto(rs.getString("NAME"), rs.getInt("POSITION"));
            return racingCarDto;
        };
    }
}
