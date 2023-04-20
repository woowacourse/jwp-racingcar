package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.dto.CarResponse;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class PlayersInfoDao {

    private static final int FIRST_PARAM = 1;
    private static final int SECOND_PARAM = 2;
    private static final int THIRD_PARAM = 3;
    private final JdbcTemplate jdbcTemplate;

    public PlayersInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(int playResultId, List<CarResponse> carResponses) {
        String sql = "insert into players_info (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarResponse carResponse = carResponses.get(i);
                ps.setString(FIRST_PARAM, carResponse.getName());
                ps.setInt(SECOND_PARAM, carResponse.getPosition());
                ps.setInt(THIRD_PARAM, playResultId);
            }

            @Override
            public int getBatchSize() {
                return carResponses.size();
            }
        });
    }

}
