package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dto.CarForNameAndPosition;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class PlayResultDao {

    private static final int FIRST_PARAM = 1;
    private static final int SECOND_PARAM = 2;
    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int returnPlayResultIdAfterInsert(int count, String winners) {
        String sql = "insert into play_result (count, winners) values (?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(FIRST_PARAM, count);
            ps.setString(SECOND_PARAM, winners);
            return ps;
        }, keyHolder);
        return (int) keyHolder.getKey();
    }

    public List<String> findAllPlayRecords() {
        String sql = "select winners from play_result";
        return this.jdbcTemplate.query(sql,
                (resultSet, rowNum) -> resultSet.getString("winners"));
    }

    public List<CarForNameAndPosition> findPlayRecordsByWinner(String winners, int gameId) {
        String sql = "select play_result.winners, players_info.name, players_info.position from play_result "
                + "join players_info on play_result.id = players_info.play_result_id"
                + " where play_result.winners = ? and play_result.id = ?";
        return this.jdbcTemplate.query(sql,
                (resultSet, rowNum) -> new CarForNameAndPosition(resultSet.getString("name"), resultSet.getInt("position")), winners, gameId);
    }
}
