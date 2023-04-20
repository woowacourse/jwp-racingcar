package racingcar.repository.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.GameEntity;

@Repository
public class JdbcGameDao implements GameDao {

    private final SimpleJdbcInsert jdbcInsert;

    public JdbcGameDao(final DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("game")
            .usingColumns("trial_count")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final GameEntity gameEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(gameEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }
}
