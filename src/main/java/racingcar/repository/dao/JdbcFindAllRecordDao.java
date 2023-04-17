package racingcar.repository.dao;

import java.util.List;

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
        resultSet.getInt("position")
    );

    public JdbcFindAllRecordDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Record> findAllRecords() {
        final String sql = "SELECT p.game_id, u.name, p.position FROM position AS p "
            + "LEFT OUTER JOIN game AS g ON p.game_id = g.id "
            + "LEFT OUTER JOIN users AS u ON p.users_id = u.id";
        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
