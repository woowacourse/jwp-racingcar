package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.vo.CarName;
import racingcar.domain.vo.Position;
import racingcar.entity.CarInfoEntity;
import racingcar.utils.RandomNumberGenerator;

@Repository
public class RacingDAO {

    private JdbcTemplate jdbcTemplate;

    public RacingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert() {
        String sql = "INSERT INTO racing () values ()";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    new String[]{"id"});
                return preparedStatement;
            },
            keyHolder
        );

        return keyHolder.getKey().intValue();
    }

    public void insert(CarInfoEntity carInfoEntity) {
        String sql = "INSERT INTO car_info (racing_id, name, position, is_winner) values (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, carInfoEntity.getRacingId());
            preparedStatement.setString(2, carInfoEntity.getName());
            preparedStatement.setInt(3, carInfoEntity.getPosition());
            preparedStatement.setBoolean(4, carInfoEntity.isWinner());
            return preparedStatement;
        });
    }

    public List<Integer> findRacingIds() {
        String sql = "SELECT id FROM racing";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getInt("id"));
    }

    public List<String> findWinnersByRacingId(int id) {
        String sql = "SELECT name FROM car_info WHERE racing_id = ? AND is_winner = true";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> resultSet.getString("name")
            , id);
    }

    public Cars findCarsById(int id) {
        String sql = "SELECT name, position FROM car_info WHERE racing_id = ?";
        return new Cars(jdbcTemplate.query(sql,
            (resultSet, rowNum) -> new Car(
                CarName.of(resultSet.getString("name")),
                Position.of(resultSet.getInt("position"))
            ), id), RandomNumberGenerator.makeInstance());
    }

}
