package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;

@Repository
public class RacingWebDao implements SimpleDao{

    private final JdbcTemplate jdbcTemplate;

    public RacingWebDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(final CarDto carDto) {
        String query = "INSERT INTO CAR(name, position, is_winner, track_id) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, carDto.getName(), carDto.getPosition(), carDto.getIsWinner(), carDto.getTrackId());
    }
    @Override
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

    @Override
    public List<CarDto> findAll() {
        String sql = "SELECT name, position, is_winner, track_id FROM CAR";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) ->
                        new CarDto(
                                resultSet.getString("name"),
                                resultSet.getInt("position"),
                                resultSet.getBoolean("is_winner"),
                                resultSet.getInt("track_id")
                        )
        );
    }
}
