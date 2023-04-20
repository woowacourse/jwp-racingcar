package racingcar.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcTemplateDao {

    protected final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateDao(final NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
