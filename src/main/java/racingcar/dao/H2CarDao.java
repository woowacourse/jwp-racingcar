package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;
import racingcar.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class H2CarDao implements CarDao {

    @Override
    public void saveAll(List<CarEntity> carEntities) {
        String sql = "INSERT INTO CAR(game_id, name, position, is_win) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionProvider.getConnection()) {
            for (CarEntity carEntity : carEntities) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, carEntity.getGameId());
                ps.setString(2, carEntity.getName());
                ps.setInt(3, carEntity.getPosition());
                ps.setBoolean(4, carEntity.isWin());
                ps.executeUpdate();

                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
