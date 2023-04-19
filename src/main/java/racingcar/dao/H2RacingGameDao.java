package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.RacingGameEntity;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class H2RacingGameDao implements RacingGameDao {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<RacingGameEntity> racingGameRowMapper = (resultSet, rowNum) -> new RacingGameEntity(
            resultSet.getInt("id"),
            resultSet.getInt("count"),
            resultSet.getTimestamp("created_at")
    );

    public H2RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(RacingGameEntity racingGameEntity) {
        String sql = "INSERT INTO RACING_GAME(count) VALUES (?)";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, racingGameEntity.getCount());
            return ps;
        }, generatedKeyHolder);
        return (int) generatedKeyHolder.getKey();
    }

    @Override
    public List<RacingGameEntity> findEndedRacingGameEntities() {
        String sql = "SELECT * FROM RACING_GAME";
        return this.jdbcTemplate.query(sql, racingGameRowMapper);
    }
}
