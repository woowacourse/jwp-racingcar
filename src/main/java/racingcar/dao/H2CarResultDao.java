package racingcar.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarResultDto;
import racingcar.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class H2CarResultDao implements CarResultDao {

    @Override
    public void save(int gameId, List<CarResultDto> carResultDtos) {
        String sql = "INSERT INTO CAR(game_id, name, position, is_win) VALUES (?,?,?,?)";

        try (Connection connection = ConnectionProvider.getConnection()) {
            for (CarResultDto carResultDto : carResultDtos) {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, gameId);
                ps.setString(2, carResultDto.getName());
                ps.setInt(3, carResultDto.getPosition());
                ps.setBoolean(4, carResultDto.isWin());
                ps.executeUpdate();

                ps.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CarResultDto> findAll() {
        String sql = "SELECT * FROM CAR";

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            final ResultSet resultSet = ps.executeQuery();

            List<CarResultDto> carResultDtos = new ArrayList<>();

            while (resultSet.next()) {
                final int gameId = resultSet.getInt("game_id");
                final String name = resultSet.getString("name");
                final int position = resultSet.getInt("position");
                final boolean isWin = resultSet.getBoolean("is_win");
                carResultDtos.add(new CarResultDto(gameId, name, position, isWin));
            }

            ps.close();
            return carResultDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
