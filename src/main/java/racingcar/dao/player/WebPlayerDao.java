package racingcar.dao.player;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.entity.PlayerEntity;

@Repository
public class WebPlayerDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public WebPlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerEntity> rowMapper = (resultSet, rowNum) -> new PlayerEntity(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    @Override
    public Long save(final String name) {
        final String sql = "INSERT INTO PLAYER(name) VALUES(?) ";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, name);
            return preparedStatement;
        }, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
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
