package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.CarEntity;

import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Component
public class CarDao {

    private final JdbcTemplate template;

    public CarDao(final JdbcTemplate template) {
        this.template = template;
    }

    public Long save(final CarEntity carEntity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO CAR (name, position, game_id) VALUES (?, ?, ?)";
        template.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, carEntity.getName());
            preparedStatement.setInt(2, carEntity.getPosition());
            preparedStatement.setLong(3, carEntity.getGameId());
            return preparedStatement;
        }, keyHolder);

        return ((long) keyHolder.getKeys().get("id"));
    }

    public List<CarEntity> findByGameId(final Long gameId) {
        return template.query("SELECT * FROM CAR WHERE game_id = ?",
                (rs, rowNum) -> new CarEntity(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getLong(4)
                ), gameId);
    }
}
