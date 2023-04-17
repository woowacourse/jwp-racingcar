package racingcar.dao;


import static racingcar.dao.ObjectMapper.carDTOMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;
import racingcar.dto.CarDTO;

@Repository
public class JdbcCarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void batchInsert(final List<CarEntity> carEntities) {
        final String sql = "INSERT INTO car(name, position, game_id, is_win) VALUES (?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarEntity carEntity = carEntities.get(i);
                ps.setString(1, carEntity.getName());
                ps.setInt(2, carEntity.getPosition());
                ps.setLong(3, carEntity.getGameId());
                ps.setBoolean(4, carEntity.getIsWin());
            }
            @Override
            public int getBatchSize() {
                return carEntities.size();
            }
        });
    }


    @Override
    public List<CarDTO> selectAll(final int gameId) {
        final String sql = "SELECT name, position FROM car where game_id = ?";
        return jdbcTemplate.query(sql, carDTOMapper, gameId);
    }

    @Override
    public List<String> selectWinners(final int gameId){
        final String sql = "SELECT name FROM car where game_id = ? and is_win = TRUE";
        return jdbcTemplate.queryForList(sql, String.class, gameId);
    }
}
