package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
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
}
