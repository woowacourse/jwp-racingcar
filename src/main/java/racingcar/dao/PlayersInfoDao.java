package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarParam;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlayersInfoDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayersInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int playResultId, List<CarParam> carParams) {
        String sql = "insert into players_info (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarParam carParam = carParams.get(i);
                ps.setString(1, carParam.getName());
                ps.setInt(2, carParam.getPosition());
                ps.setInt(3, playResultId);
            }

            @Override
            public int getBatchSize() {
                return carParams.size();
            }
        });
    }

}
