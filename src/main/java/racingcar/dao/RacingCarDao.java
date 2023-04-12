package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.entity.RacingGameEntity;

@Repository
public class RacingCarDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RacingCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(RacingGameEntity racingGameEntity) {
        String sql = "INSERT INTO RACING_GAME(count, winners, created_at) VALUES(?, ?, ?)";
        int id = jdbcTemplate.update(sql, racingGameEntity.getCount(), racingGameEntity.getWinners(), racingGameEntity.getCreatedAt());
        String sqlForRacingGameEntity = "INSERT INTO RACING_CAR(position, name, racing_game_id) VALUES(?, ?, ?)";
        racingGameEntity.getRacingCars().stream()
                .forEach(car -> jdbcTemplate.update(sqlForRacingGameEntity, car.getPosition(), car.getName(), id));
    }
}
