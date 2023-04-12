package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class H2CarDao implements CarDao {

    @Override
    public void save(List<CarDto> carDtos) {
        String sql = "INSERT INTO CAR(game_id, name, position, is_win) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionProvider.getConnection()) {
            for (CarDto carDto : carDtos) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, carDto.getGameId());
                ps.setString(2, carDto.getName());
                ps.setInt(3, carDto.getPosition());
                ps.setBoolean(4, carDto.isWin());
                ps.executeUpdate();

                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
