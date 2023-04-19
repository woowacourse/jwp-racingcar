package racingcar.dao;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.RacingGameDto;

@Repository
public class RacingGameDaoImpl implements RacingGameDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RacingGameDaoImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public RacingGameDto insertRacingGame(final RacingGameDto racingGameDto) {
        String sql = "INSERT INTO RACING_GAME (trial_count) VALUES(:trialCount)";

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(racingGameDto);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);
        int id = (int) keyHolder.getKeys().get("id");

        return RacingGameDto.of(id, racingGameDto.getTrialCount());
    }

    @Override
    public List<RacingGameDto> selectAllResults() {
        String sql = "select * from RACING_GAME order by id desc";

        List<RacingGameEntity> entities = namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
            int id = rs.getInt("id");
            int trialCount = rs.getInt("trial_count");
            return new RacingGameEntity(id, trialCount);
        });

        return entities.stream()
                .map(entity -> RacingGameDto.of(entity.getId(), entity.getTrialCount()))
                .collect(Collectors.toList());
    }

}
