package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import racingcar.entity.PlayResult;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PlayResultDao {

    private static final int FIRST_PARAM = 1;
    private static final int SECOND_PARAM = 2;
    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int returnPlayResultIdAfterInsert(int count, String winners) {
        String sql = "insert into play_result (count, winners) values (?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(FIRST_PARAM, count);
            ps.setString(SECOND_PARAM, winners);
            return ps;
        }, keyHolder);
        return (int) keyHolder.getKey();
    }

    public List<PlayResult> findAllPlayResults() {
        String sql = "select * from play_result";
        return this.jdbcTemplate.query(sql,
                (resultSet, rowNum) -> {
                    Integer id = resultSet.getInt("id");
                    Integer count = resultSet.getInt("count");
                    String winners = resultSet.getString("winners");
                    LocalDateTime createdAt = resultSet.getObject("created_at", LocalDateTime.class);
                    return new PlayResult(id, count, winners, createdAt);
                });
    }
}
