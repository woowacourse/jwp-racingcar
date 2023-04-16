package racingcar.repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import racingcar.repository.mapper.RacingGameDto;

@Repository
public class RacingGameRepository {

    private final JdbcTemplate jdbcTemplate;

    public RacingGameRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<RacingGameDto> racingGameRowMapper = (resultSet, rowNum) -> new RacingGameDto(
            resultSet.getInt("id"),
            resultSet.getString("winners"),
            resultSet.getObject("created_at", LocalDateTime.class),
            resultSet.getInt("trial")
    );

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

    public Optional<RacingGameDto> findById(final int id) {
        final String sql = "SELECT * FROM RACING_GAME WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, racingGameRowMapper, id));
    }

    public List<RacingGameDto> findAll() {
        final String sql = "SELECT * FROM RACING_GAME";
        return jdbcTemplate.query(sql, racingGameRowMapper);
    }
}
