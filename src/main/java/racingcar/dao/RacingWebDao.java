package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

@Repository
public class RacingWebDao implements RacingDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingWebDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CarDto> actorRowMapper = (resultSet, rowNum) -> {
        CarDto carDto = new CarDto(
                resultSet.getString("name"),
                resultSet.getInt("position"),
                resultSet.getBoolean("is_winner"),
                resultSet.getInt("track_id")
        );
        return carDto;
    };

    public void saveCar(final CarDto carDto) {
        String query = "INSERT INTO CAR(name, position, is_winner, track_id) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, carDto.getName(), carDto.getPosition(), carDto.getIsWinner(), carDto.getTrackId());
    }

    public void saveWithBatch(final List<CarDto> carDtos) {
        String query = "INSERT INTO CAR(name, position, is_winner, track_id) values (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(query,
                carDtos,
                100,
                (PreparedStatement ps, CarDto carDto) -> {
                    ps.setString(1, carDto.getName());
                    ps.setInt(2, carDto.getPosition());
                    ps.setBoolean(3, carDto.getIsWinner());
                    ps.setInt(4, carDto.getTrackId());
                });
    }

    public Integer saveTrack(final TrackDto trackDto) {
        String query = "INSERT INTO TRACK(trial_times) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setInt(1, trackDto.getTrialTimes());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public List<Integer> findAllTrackIds() {
        String query = "SELECT ID FROM TRACK";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("id"));
    }

    public Cars findAllCarsByTrackId(int trackId) {
        String query = "SELECT * FROM CAR WHERE track_id = ?";
        List<CarDto> carDtos = jdbcTemplate.query(query, actorRowMapper, trackId);

        List<Car> cars = new ArrayList<>();
        for (CarDto carDto : carDtos) {
            cars.add(new Car(carDto.getName(), carDto.getPosition()));
        }
        return new Cars(cars);
    }
}
