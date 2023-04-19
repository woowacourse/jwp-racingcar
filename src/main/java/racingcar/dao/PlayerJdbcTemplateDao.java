package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.PlayerEntity;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PlayerJdbcTemplateDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerJdbcTemplateDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerEntity> actorRowMapper = (resultSet, rowNum) -> new PlayerEntity(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

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

    public Optional<PlayerEntity> findByName(final String name) {
        final String sql = "SELECT * FROM PLAYER WHERE name = ? ";
        try {
            PlayerEntity playerEntity = jdbcTemplate.queryForObject(sql, actorRowMapper, name);
            return Optional.ofNullable(playerEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<PlayerEntity> findById(final Long id) {
        final String sql = "SELECT * FROM PLAYER WHERE id = ? ";
        try {
            PlayerEntity playerEntity = jdbcTemplate.queryForObject(sql, actorRowMapper, id);
            return Optional.ofNullable(playerEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<PlayerEntity> findAll() {
        final String sql = "SELECT * FROM PLAYER ";
        try {
            return jdbcTemplate.query(sql, actorRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
