package racingcar.repository.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.PositionEntity;

@Repository
public class JdbcPositionDao implements PositionDao {

    private final SimpleJdbcInsert jdbcInsert;

    public JdbcPositionDao(final DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("position")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final PositionEntity positionEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(positionEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }
}
