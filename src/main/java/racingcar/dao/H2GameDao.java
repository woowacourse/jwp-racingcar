package racingcar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.PlayResultDto;

@Repository
public class H2GameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public H2GameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long insert(int trialCount, List<String> winners) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO game (trialcount, winners) VALUES (?, ?)";

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            preparedStatement.setString(2, String.join(",", winners));
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<PlayResultDto> selectAll() {
        final String sql = "SELECT * FROM game";
        RowMapper<PlayResultDto> playResult = (rs, rowNum)
                -> new PlayResultDto(
                rs.getInt("id"),
                List.of(rs.getString("winners").split(",")
                )
        );
        return jdbcTemplate.query(sql, playResult);
    }
}
