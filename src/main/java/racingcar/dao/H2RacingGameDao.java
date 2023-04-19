package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.RacingGameEntity;
import racingcar.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class H2RacingGameDao implements RacingGameDao {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<RacingGameEntity> racingGameRowMapper = (resultSet, rowNum) -> new RacingGameEntity(
            resultSet.getInt("id"),
            resultSet.getInt("count"),
            resultSet.getTimestamp("created_at")
    );

    public H2RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(RacingGameEntity racingGameEntity) {
        String sql = "INSERT INTO RACING_GAME(count) VALUES (?)";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, racingGameEntity.getCount());
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

    @Override
    public List<RacingGameEntity> findEndedRacingGameEntities() {
        String sql = "SELECT * FROM RACING_GAME";
        return this.jdbcTemplate.query(sql, racingGameRowMapper);
    }
}
