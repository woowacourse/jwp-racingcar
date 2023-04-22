package racingcar.repository;

import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.domain.entity.Race;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class RaceH2Repository implements RaceRepository {
    private final JdbcTemplate jdbcTemplate;

    public RaceH2Repository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Integer> saveRace(Race race) {
        String sql = "INSERT INTO racing (trial_Count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
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
                return Optional.of(key.intValue());
            }
            return Optional.empty();
        } catch (JdbcUpdateAffectedIncorrectNumberOfRowsException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Integer> findAllId() {
        String sql = "SELECT id FROM racing";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"));
    }
}
