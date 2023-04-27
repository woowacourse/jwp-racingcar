package racingcar.repository.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import racingcar.repository.dto.ResultDto;

@Repository
public class JdbcFindAllResultsDao implements FindAllResultsDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<ResultDto> actorRowMapper = (resultSet, rowNum) -> new ResultDto(
        resultSet.getLong("game_id"),
        resultSet.getString("name"),
        resultSet.getInt("position"),
        resultSet.getBoolean("is_winner")
    );

    public JdbcFindAllResultsDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ResultDto> findAll() {
        final String sql = "SELECT c.game_id, p.name, c.position, w.id IS NOT NULL AS is_winner FROM car AS c "
            + "LEFT OUTER JOIN game AS g ON c.game_id = g.id "
            + "LEFT OUTER JOIN player AS p ON c.player_id = p.id "
            + "LEFT OUTER JOIN winner AS w ON (g.id = w.game_id AND p.id = w.player_id)";
        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
