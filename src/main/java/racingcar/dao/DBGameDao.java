package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class DBGameDao {
    private final JdbcTemplate jdbcTemplate;

    public DBGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long insertGame(int moveCount) {
        String sql = "INSERT INTO game (trial_count, date) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, moveCount);
            pst.setDate(2, Date.valueOf(LocalDate.now()));
            return pst;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<Long> selectAllGameIds() {
        String sql = "SELECT id FROM game";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getLong("id"));
    }

    public int selectMoveCountById(long id) {
        String sql = "SELECT trial_count FROM game WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, id);
    }

}
