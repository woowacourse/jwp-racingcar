package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.PlayerResultEntity;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayerResultDAO {

    private final JdbcTemplate jdbcTemplate;

    public PlayerResultDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int returnTableIdAfterInsert(int count, List<Car> winners) {
        String sql = "insert into play_result (count, winners) values (?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, count);
            ps.setString(2, makeWinnersString(winners));
            return ps;
        }, keyHolder);
        return (int) keyHolder.getKey();
    }

    private String makeWinnersString(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public List<PlayerResultEntity> findAll() {
        String sql = "select * from play_result";

        return jdbcTemplate.query(sql, playerResultRowMapper());
    }

    private RowMapper<PlayerResultEntity> playerResultRowMapper() {
        return (result, columnRow) -> {
            final PlayerResultEntity playerResultEntity = new PlayerResultEntity();
            playerResultEntity.setId(result.getInt("id"));
            playerResultEntity.setCount(result.getInt("count"));
            playerResultEntity.setWinners(result.getString("winners"));
            playerResultEntity.setDateTime(result.getTimestamp("created_at"));
            return playerResultEntity;
        };
    }
}
