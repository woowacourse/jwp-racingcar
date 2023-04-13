package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

import java.sql.PreparedStatement;

@Repository
public class RacingDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final CarDto carDto) {
        String query = "INSERT INTO CAR(name, position, is_winner, track_id) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, carDto.getName(), carDto.getPosition(), carDto.getIsWinner(), carDto.getTrackId());
    }

    public Integer save(final TrackDto trackDto) {
        String query = "INSERT INTO TRACK(trial_times) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setInt(1, trackDto.getTrialTimes());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}
