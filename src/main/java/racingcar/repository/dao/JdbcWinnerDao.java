package racingcar.repository.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.WinnerEntity;

@Repository
public class JdbcWinnerDao implements WinnerDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<WinnerEntity> actorRowMapper = (resultSet, rowNum) -> new WinnerEntity(
        resultSet.getLong("id"),
        resultSet.getLong("game_id"),
        resultSet.getLong("users_id")
    );

    public JdbcWinnerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("winner")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final WinnerEntity winnerEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(winnerEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public List<WinnerEntity> findByGameId(final long gameId) {
        final String sql = "SELECT id, game_id, users_id "
                + "FROM winner "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
