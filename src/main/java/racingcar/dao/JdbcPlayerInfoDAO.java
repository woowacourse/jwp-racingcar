package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.PlayerInfoEntity;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class JdbcPlayerInfoDAO implements PlayerInfoDAO{

    private JdbcTemplate jdbcTemplate;

    public JdbcPlayerInfoDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(int playResultId, List<Car> cars) {
        String sql = "insert into player_info (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql,
                cars,
                10,
                (PreparedStatement ps, Car car) -> {
                    ps.setString(1, car.getName());
                    ps.setInt(2, car.getPosition());
                    ps.setInt(3, playResultId);
                });

    }

    @Override
    public List<PlayerInfoEntity> findPlayerByPlayResultId(int playResultId) {
        String sql = "select * from player_info where play_result_id = ?";
        return jdbcTemplate.query(sql, (result, column) -> {
                    final PlayerInfoEntity playerInfoEntity = new PlayerInfoEntity(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getInt("position"),
                            result.getInt("position")
                    );
                    return playerInfoEntity;
                },
                playResultId
        );
    }
}
