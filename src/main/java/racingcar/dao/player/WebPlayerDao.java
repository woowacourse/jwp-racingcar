package racingcar.dao.player;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerEntity;

@Repository
public class WebPlayerDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public WebPlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<PlayerEntity> rowMapper = (resultSet, rowNum) -> new PlayerEntity(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    @Override
    public Long save(final String name) {
        final HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public Optional<PlayerEntity> findByName(final String name) {
        final String sql = "SELECT * FROM PLAYER WHERE name = ? ";
        try {
            PlayerEntity playerEntity = jdbcTemplate.queryForObject(sql, rowMapper, name);
            return Optional.ofNullable(playerEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public String findNameById(final Long id) {
        final String sql = "SELECT name FROM Player WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}
