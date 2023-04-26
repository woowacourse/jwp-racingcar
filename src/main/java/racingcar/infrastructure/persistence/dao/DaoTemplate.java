package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

public final class DaoTemplate<T> {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final String tableName;

    public DaoTemplate(final JdbcTemplate jdbcTemplate, final String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(tableName)
                .usingGeneratedKeyColumns("id");
    }

    public void batchUpdate(List<T> entities) {
        final BeanPropertySqlParameterSource[] parameterSources = entities.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(BeanPropertySqlParameterSource[]::new);

        simpleJdbcInsert.executeBatch(parameterSources);
    }

    public <E> List<E> findByGameId(final RowMapper<E> mapper, final Long gameId) {
        final String sql = String.format("SELECT * FROM %s WHERE game_id = ?", tableName);
        return jdbcTemplate.query(sql, mapper, gameId);
    }

    public <E> List<E> findAll(final RowMapper<E> mapper) {
        final String sql = String.format("SELECT * FROM %s", tableName);
        return jdbcTemplate.query(sql, mapper);
    }
}
