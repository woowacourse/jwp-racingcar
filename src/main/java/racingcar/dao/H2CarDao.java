package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;
import racingcar.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class H2CarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<CarEntity> carRowMapper = (resultSet, rowNum) -> new CarEntity(
            resultSet.getInt("game_id"),
            resultSet.getString("name"),
            resultSet.getInt("position")
    );

    public H2CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<CarEntity> carEntities) {
        String sql = "INSERT INTO CAR(game_id, name, position) VALUES (?,?,?)";

        try (Connection connection = ConnectionProvider.getConnection()) {
            for (CarEntity carEntity : carEntities) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, carEntity.getGameId());
                ps.setString(2, carEntity.getName());
                ps.setInt(3, carEntity.getPosition());
                ps.executeUpdate();

                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CarEntity> findEndedCars() {
        String sql = "SELECT * FROM CAR";
        return this.jdbcTemplate.query(sql, carRowMapper);
    }
}
