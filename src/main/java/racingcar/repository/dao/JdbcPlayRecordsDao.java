package racingcar.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcPlayRecordsDao implements PlayRecordsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayRecordsDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(final int racingCount) {
        // TODO Entity 받도록 바꾸고 파라미터 매핑
        jdbcTemplate.update("INSERT INTO play_records (trial_count) VALUES (?)", racingCount);
    }

    @Override
    public long getLastId() {
        return jdbcTemplate.queryForObject("SELECT MAX(id) FROM play_records", Long.class);
    }

    @Override
    public void clear() {
        jdbcTemplate.update("DELETE FROM play_records");
    }
}
