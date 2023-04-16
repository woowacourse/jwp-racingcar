package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.domain.entity.Race;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

public class RacingH2Repository implements RaceRepository {
    private final JdbcTemplate jdbcTemplate;

    public RacingH2Repository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveRace(Race race) {
        String sql = "INSERT INTO racing (trial_Count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
                    preparedStatement.setInt(1, race.getTrialCount());
                    return preparedStatement;
                },
                keyHolder
        );

        Number key = keyHolder.getKey();
        if (key != null) {
            return key.intValue();
        }

        throw new IllegalStateException("레이싱 정보를 저장하고 키를 가져오지 못했습니다.");
    }

    @Override
    public List<Integer> findAllId() {
        String sql = "SELECT id FROM racing";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"));
    }
}
