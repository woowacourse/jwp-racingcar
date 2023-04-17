package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingCarDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayersHistoryDao {
    private final SimpleJdbcInsert insertActor;
    private final JdbcTemplate jdbcTemplate;

    public PlayersHistoryDao(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("players_result")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<RacingCarDto> racingCarDtoRowMapper = (resultSet, rowNum) -> {
        RacingCarDto racingCarDto = new RacingCarDto(
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return racingCarDto;
    };

    public void insertResult(final List<RacingCarDto> racingCarDtos, final int resultId) {
        for (final RacingCarDto racingCarDto : racingCarDtos) {
            final Map<String, Object> params = new HashMap<>();
            params.put("name", racingCarDto.getName());
            params.put("position", racingCarDto.getPosition());
            params.put("result_id", resultId);
            insertActor.execute(params);
        }
    }

    public List<RacingCarDto> getResult(final Long resultId) {
        String sql = "select name, position from players_result where result_id = ?";
        return jdbcTemplate.query(sql, racingCarDtoRowMapper, resultId);
    }
}
