package racingcar.infrastructure.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;

@Component
public class WinnerDao {

    private static final String WINNER_TABLE_NAME = "WINNER";
    
    private final DaoTemplate<WinnerEntity> daoTemplate;
    private final RowMapper<WinnerEntity> mapper = (rs, rowNum) -> new WinnerEntity(
            rs.getString("name"),
            rs.getLong("game_id")
    );

    public WinnerDao(final JdbcTemplate template) {
        this.daoTemplate = new DaoTemplate<>(template, WINNER_TABLE_NAME);
    }

    public void save(final List<WinnerEntity> entities) {
        daoTemplate.batchUpdate(entities);
    }

    public List<WinnerEntity> findByGameId(final Long gameId) {
        return daoTemplate.findByGameId(mapper, gameId);
    }

    public List<WinnerEntity> findAll() {
        return daoTemplate.findAll(mapper);
    }
}
