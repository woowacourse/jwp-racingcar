package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.vo.Trial;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@Component
public class RacingH2Dao implements RacingDao {
    private final JdbcTemplate jdbcTemplate;

    public RacingH2Dao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveRacing(Trial trial) {
        String sql = "INSERT INTO racing (trial_Count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
                    preparedStatement.setInt(1, trial.getValue());
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
}
