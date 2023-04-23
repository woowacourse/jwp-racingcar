package racingcar.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import racingcar.dto.CarForNameAndPosition;
import racingcar.entity.PlayersInfo;

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

    public void insert(int playResultId, List<CarForNameAndPosition> carResponse) {
        String sql = "insert into players_info (name, position, play_result_id) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarForNameAndPosition carForNameAndPosition = carResponse.get(i);
                ps.setString(FIRST_PARAM, carForNameAndPosition.getName());
                ps.setInt(SECOND_PARAM, carForNameAndPosition.getPosition());
                ps.setInt(THIRD_PARAM, playResultId);
            }

            @Override
            public int getBatchSize() {
                return carResponse.size();
            }
        });
    }

    public List<PlayersInfo> findPlayersInfosByPlayResultId(int playResultId) {
        String sql = "select * from players_info where play_result_id = ?";
        return this.jdbcTemplate.query(sql,
                (resultSet, rowNum) -> {
                    Integer id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Integer position = resultSet.getInt("position");
                    return new PlayersInfo(id, name, position, playResultId);
                }, playResultId);
    }

}
