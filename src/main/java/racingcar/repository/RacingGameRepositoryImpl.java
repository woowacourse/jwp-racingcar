package racingcar.repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import racingcar.repository.mapper.RacingGameInfo;

@Repository
public class RacingGameRepositoryImpl implements RacingGameRepository {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<RacingGameInfo> actorRowMapper = (resultSet, rowNum) -> {
        RacingGameInfo racingGameInfo = new RacingGameInfo(
                resultSet.getInt("id"),
                resultSet.getString("winners"),
                resultSet.getObject("created_at", LocalDateTime.class),
                resultSet.getInt("trial")
        );

        return racingGameInfo;
    };

    @Override
    public int save(final String winners, final int count) {
        final String sql = "INSERT INTO RACING_GAME(winners, trial) VALUES(?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, winners);
            ps.setInt(2, count);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Optional<RacingGameInfo> findById(final int id) {
        final String sql = "SELECT * FROM RACING_GAME WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, actorRowMapper, id));
    }
}
