package racingcar.repository;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.domain.Cars;
import racingcar.domain.Name;
import racingcar.domain.Position;
import racingcar.dto.PlayerDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlayerJdbcRepository implements PlayerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayerJdbcRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerDto> playerRowMapper = (resultSet, rowNum) -> {
        return new PlayerDto(
                new Name(resultSet.getString("name")),
                new Position(resultSet.getInt("position"))
        );
    };

    @Override
    public boolean save(final Cars cars, final int racingGameId) {
        final String sql = "INSERT INTO PLAYER(name, position, racing_game_id) VALUES(?, ?, ?)";
        final int[] updatedCounts = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                ps.setString(1, cars.getRacingCars().get(i).getNameValue());
                ps.setInt(2, cars.getRacingCars().get(i).getPositionValue());
                ps.setInt(3, racingGameId);
            }

            @Override
            public int getBatchSize() {
                return cars.getRacingCars().size();
            }
        });

        return updatedCounts.length == cars.getRacingCars().size();
    }

    @Override
    public List<PlayerDto> findByRacingGameId(final int racingGameId) {
        final String sql = "SELECT * FROM PLAYER WHERE racing_game_id = ?";
        return jdbcTemplate.query(sql, playerRowMapper, racingGameId);
    }
}
