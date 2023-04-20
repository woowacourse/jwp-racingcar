package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarParam;
import racingcar.dto.PlayRecordsResponse;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
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

    public List<PlayRecordsResponse> findAllPlayRecords() {
        String sql = "select winners from play_result";
        List<String> winnersBundle = this.jdbcTemplate.query(sql,
                (resultSet, rowNum) -> resultSet.getString("winners"));
        return winnersBundle.stream()
                .map(this::findPlayRecordsByWinner)
                .collect(Collectors.toList());
    }

    private PlayRecordsResponse findPlayRecordsByWinner(String winners) {
        String sql = "select play_result.winners, players_info.name, players_info.position from play_result "
                + "join players_info on play_result.id = players_info.play_result_id where play_result.winners = ?";
        List<CarParam> carParams = this.jdbcTemplate.query(sql,
                (resultSet, rowNum) -> new CarParam(resultSet.getString("name"), resultSet.getInt("position")), winners);
        return new PlayRecordsResponse(winners, carParams);
    }
}
