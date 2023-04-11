package racingcar.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayResultEntity;

import java.sql.PreparedStatement;

@Repository
public class PlayResultMapper {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public PlayResultMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayResultEntity> entityRowMapper = (resultSet, rowNum) -> {
        PlayResultEntity playResultEntity = PlayResultEntity.of(
                resultSet.getLong("id"),
                resultSet.getInt("trial_count"),
                resultSet.getString("winners"),
                resultSet.getTimestamp("created_at")
        );
        return playResultEntity;
    };

    public long save(PlayResultEntity playResultEntity) {
        String sql = "INSERT INTO play_result(trial_count, winners) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = (connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, playResultEntity.getTrialCount());
            ps.setString(2, playResultEntity.getWinners());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public PlayResultEntity findById(long id) {
        String sql = "SELECT * FROM play_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }
}
