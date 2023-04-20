package racingcar.repository.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.repository.dao.entity.PlayRecordEntity;

@Component
public class JdbcPlayRecordsDao implements PlayRecordsDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcPlayRecordsDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Override
    public void insert(final PlayRecordEntity playRecord) {
        namedParameterJdbcTemplate.update("INSERT INTO play_records (trial_count) VALUES (:count)",
                new BeanPropertySqlParameterSource(playRecord));
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
