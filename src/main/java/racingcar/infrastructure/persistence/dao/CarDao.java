package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.CarEntity;

import java.util.List;

@Component
public class CarDao {

    private final JdbcTemplate template;
    private final RowMapper<CarEntity> mapper = (rs, rowNum) -> new CarEntity(
            rs.getString("name"),
            rs.getInt("position"),
            rs.getLong("game_id")
    );

    public CarDao(final JdbcTemplate template) {
        this.template = template;
    }

    public void save(final List<CarEntity> entities) {
        template.batchUpdate("INSERT INTO CAR (name, position, game_id) VALUES(?, ?, ?)",
                entities,
                50,
                (ps, entity) -> {
                    ps.setString(1, entity.getName());
                    ps.setInt(2, entity.getPosition());
                    ps.setLong(3, entity.getGameId());
                });
    }

    public List<CarEntity> findByGameId(final Long gameId) {
        return template.query("SELECT * FROM CAR WHERE game_id = ?", mapper, gameId);
    }

    public List<CarEntity> findAll() {
        return template.query("SELECT * FROM CAR", mapper);
    }
}
