package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarParam;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayResultDao {

    private static final int FIRST_PARAM = 1;
    private static final int SECOND_PARAM = 2;
    private static final String DELIMITER = ",";
    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int returnPlayResultIdAfterInsert(int count, List<CarParam> winners) {
        String sql = "insert into play_result (count, winners) values (?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(FIRST_PARAM, count);
            ps.setString(SECOND_PARAM, makeWinnersString(winners));
            return ps;
        }, keyHolder);
        return (int) keyHolder.getKey();
    }

    private String makeWinnersString(List<CarParam> winners) {
        return winners.stream()
                .map(CarParam::getName)
                .collect(Collectors.joining(DELIMITER));
    }
}
