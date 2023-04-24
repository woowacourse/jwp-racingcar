package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(List<Player> players) {
        String sql = "insert into PLAYER (name, position, game_id) values (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Player player = players.get(i);
                ps.setString(1, player.getName());
                ps.setInt(2, player.getPosition());
                ps.setInt(3, player.getGameId());
            }

            @Override
            public int getBatchSize() {
                return players.size();
            }
        });
    }

    public List<Player> selectAllByGameId(int gameId) {
        final var query = "SELECT * FROM PLAYER WHERE game_id = ?";

        return jdbcTemplate.query(query,
                (resultSet, rowNum) -> {
                    //Car car, int gameId
                    String name = resultSet.getString("name");
                    int position = resultSet.getInt("position");
                    Car car = new Car(name, position);
                    return Player.of(car, gameId);
                }, gameId
        );
    }
}
