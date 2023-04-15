package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import racingcar.dto.CarInfoDto;
import racingcar.domain.vo.Trial;

import java.sql.PreparedStatement;

public class InsertingDAO {

    private JdbcTemplate jdbcTemplate;

    public InsertingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Trial trial) {
        String sql = "INSERT INTO racing (trialCount) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            con -> {
                PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setInt(1, trial.getValue());
                return preparedStatement;
            },
            keyHolder
        );

        return keyHolder.getKey().intValue();
    }

    public void insert(CarInfoDto carInfoDto) {
        String sql = "INSERT INTO car_info (racing_id, name, position, is_winner) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            carInfoDto.getRacingId(),
            carInfoDto.getName(),
            carInfoDto.getPosition(),
            carInfoDto.isWinner());
    }
}
