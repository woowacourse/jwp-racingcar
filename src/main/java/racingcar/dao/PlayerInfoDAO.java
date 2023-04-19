package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.PlayerInfo;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlayerInfoDAO {

    private JdbcTemplate jdbcTemplate;

    public PlayerInfoDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int parentId, List<Car> cars) {
        String sql = "insert into player_info (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql,
                cars,
                10,
                (PreparedStatement ps, Car car) -> {
                    ps.setString(1, car.getName());
                    ps.setInt(2, car.getPosition());
                    ps.setInt(3, parentId);
                });

    }

    public List<PlayerInfo> findPlayerByResultId(Integer play_result_id) {
        String sql = "select * from player_info where play_result_id = ?";
        return jdbcTemplate.query(sql, (result, column) -> {
                    final PlayerInfo playerInfo = new PlayerInfo();
                    playerInfo.setId(result.getInt("id"));
                    playerInfo.setName(result.getString("name"));
                    playerInfo.setPosition(result.getInt("position"));
                    playerInfo.setPlayResultId(result.getInt("play_result_id"));
                    return playerInfo;
                },
                play_result_id
        );
    }
}
