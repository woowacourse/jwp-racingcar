package racingcar.dao;

import org.springframework.stereotype.Repository;
import racingcar.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class H2RacingGameDao implements RacingGameDao {

    @Override
    public int save(int count) {
        String sql = "INSERT INTO RACING_GAME(count) VALUES (?)";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, count);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

            throw new IllegalStateException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
