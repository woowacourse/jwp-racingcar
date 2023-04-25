package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.CarEntity;

import java.util.List;

@Component
public class CarDao {

    private static final String CAR_TABLE_NAME = "CAR";

    private final DaoTemplate<CarEntity> daoTemplate;
    private final RowMapper<CarEntity> mapper = (rs, rowNum) -> new CarEntity(
            rs.getString("name"),
            rs.getInt("position"),
            rs.getLong("game_id")
    );

    public CarDao(final JdbcTemplate template) {
        this.daoTemplate = new DaoTemplate<>(template, CAR_TABLE_NAME);
    }

    public void save(final List<CarEntity> entities) {
        daoTemplate.batchUpdate(entities);
    }

    public List<CarEntity> findByGameId(final Long gameId) {
        return daoTemplate.findByGameId(mapper, gameId);
    }

    public List<CarEntity> findAll() {
        return daoTemplate.findAll(mapper);
    }
}
