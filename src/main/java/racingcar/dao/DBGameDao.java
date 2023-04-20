package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.GameEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class DBGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;

    public DBGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<GameEntity> gameEntityRowMapper = (resultSet, rowNum) -> new GameEntity(
            resultSet.getLong("id"),
            resultSet.getInt("move_count"),
            resultSet.getDate("date")
    );

    public long insert(int moveCount) {
        String sql = "INSERT INTO game (move_count, date) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, moveCount);
            pst.setDate(2, Date.valueOf(LocalDate.now()));
            return pst;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public List<GameEntity> selectAll() {
        String sql = "SELECT * FROM game";
        return jdbcTemplate.query(sql, gameEntityRowMapper);
    }

}
