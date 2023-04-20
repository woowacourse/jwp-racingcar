package racingcar.repository.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.Record;

@Repository
public class JdbcFindAllRecordDao implements FindAllRecordsDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Record> actorRowMapper = (resultSet, rowNum) -> new Record(
        resultSet.getLong("game_id"),
        resultSet.getString("name"),
        resultSet.getInt("position"),
        resultSet.getBoolean("is_winner")
    );

    public JdbcFindAllRecordDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Record> findAll() {
        final String sql = "SELECT p.game_id, u.name, p.position, w.id IS NOT NULL AS is_winner FROM position AS p "
            + "LEFT OUTER JOIN game AS g ON p.game_id = g.id "
            + "LEFT OUTER JOIN users AS u ON p.users_id = u.id "
            + "LEFT OUTER JOIN winner AS w ON (g.id = w.game_id AND u.id = w.users_id)";
        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
