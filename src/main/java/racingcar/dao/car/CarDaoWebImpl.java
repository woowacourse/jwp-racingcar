package racingcar.dao.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dto.db.CarDto;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public final class CarDaoWebImpl implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarDaoWebImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(final CarDto dto) {
        String sql = "INSERT INTO car (player_name, final_position, is_winner, game_result_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, dto.getName());
            ps.setInt(2, dto.getPosition());
            ps.setBoolean(3, dto.isWinner());
            ps.setLong(4, dto.getId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void saveAll(final List<CarDto> dtos) {
        for (CarDto dto : dtos) {
            save(dto);
        }
    }
}
