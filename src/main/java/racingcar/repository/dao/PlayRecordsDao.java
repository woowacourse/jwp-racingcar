package racingcar.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PlayRecordsDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayRecordsDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final int count) {
        jdbcTemplate.update("INSERT INTO play_records (trial_count) VALUES (?)", count);
    }

    public long getLastId() {
        return jdbcTemplate.queryForObject("SELECT id FROM play_records ORDER BY id DESC LIMIT 1", Long.class);
    }

    public void clear() {
        jdbcTemplate.update("DELETE FROM play_records");
    }
}
