package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarInfoEntity;
import racingcar.domain.vo.Trial;

import java.sql.PreparedStatement;

@Repository
public class InsertingDAO {

    private JdbcTemplate jdbcTemplate;

    public InsertingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Trial trial) {
        String sql = "INSERT INTO racing (trial_count) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    new String[]{"id"});
                preparedStatement.setInt(1, trial.getValue());
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
}
