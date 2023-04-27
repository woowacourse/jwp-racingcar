package racingcar.repository.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.CarEntity;

@Repository
public class JdbcCarDao implements CarDao {

    private final SimpleJdbcInsert jdbcInsert;

    public JdbcCarDao(final DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("car")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final CarEntity carEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(carEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }
}
