package racingcar.repository.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.WinnerEntity;

@Repository
public class JdbcWinnerDao implements WinnerDao {

    private final SimpleJdbcInsert jdbcInsert;

    public JdbcWinnerDao(final DataSource dataSource) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("winner")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final WinnerEntity winnerEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(winnerEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }
}
