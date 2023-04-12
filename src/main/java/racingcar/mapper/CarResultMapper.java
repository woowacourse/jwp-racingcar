package racingcar.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.CarResultEntity;

import java.sql.PreparedStatement;

@Repository
public class CarResultMapper {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CarResultMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<CarResultEntity> entityRowMapper = (resultSet, rowNum) -> {
        CarResultEntity carResultEntity = CarResultEntity.of(
                resultSet.getLong("id"),
                resultSet.getLong("play_result_id"),
                resultSet.getString("name"),
                resultSet.getInt("position")
        );
        return carResultEntity;
    };

    public long save(CarResultEntity carResultEntity) {
        String sql = "INSERT INTO car_result(play_result_id, name, position) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = (connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, carResultEntity.getPlayResultId());
            ps.setString(2, carResultEntity.getName());
            ps.setInt(3, carResultEntity.getPosition());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public CarResultEntity findById(long id) {
        String sql = "SELECT * FROM car_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }
}
