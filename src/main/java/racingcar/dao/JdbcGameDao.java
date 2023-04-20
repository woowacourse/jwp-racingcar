package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.GameIdDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcGameDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long insert(final int count) {
        final String sql = "INSERT INTO GAME(trial_count) VALUES (?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection con) throws SQLException {
                final PreparedStatement preparedStatement = con.prepareStatement(
                        sql, new String[]{"ID"}
                );
                preparedStatement.setInt(1, count);
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public int countRows() {
        final String sql = "select count(*) from game";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM GAME");
    }

    @Override
    public List<GameIdDTO> findAllGameIds() {
        final String sql = "SELECT id FROM GAME";
        return jdbcTemplate.query(sql, getGameIdRowMapper());
    }

    private RowMapper<GameIdDTO> getGameIdRowMapper() {
        return (resultSet, rowNum) -> new GameIdDTO(resultSet.getLong("id"));
    }
}
