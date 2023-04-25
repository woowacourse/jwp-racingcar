package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.dto.response.RacingGameWinnersDto;

@Repository
public class RacingGameDao {

    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public RacingGameDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("racing_game")
                .usingColumns("winners", "trial_count")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Long save(final String winners, final Integer trialCount) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("winners", winners);
        parameters.put("trial_count", trialCount);
        return insertActor.executeAndReturnKey(parameters).longValue();

    }

    public List<RacingGameWinnersDto> findAllWinners() {
        String sql = "select id, winners from racing_game";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    RacingGameWinnersDto racingGameWinnersDto = new RacingGameWinnersDto(
                            rs.getLong("id"),
                            rs.getString("winners")
                    );
                    return racingGameWinnersDto;
                });
    }



}
