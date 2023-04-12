package racingcar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.dto.PlayerDto;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayerDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PlayerDto> actorRowMapper = (resultSet, rowNum) -> new PlayerDto(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    public Long save(final String name) {
        final String sql = "INSERT INTO PLAYER(name) VALUES(?) ";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] {"id"});
            preparedStatement.setString(1, name);
            return preparedStatement;
        }, generatedKeyHolder);
        return Objects.requireNonNull(generatedKeyHolder.getKey()).longValue();
    }

    public Optional<PlayerDto> findByName(final String name) {
        final String sql = "SELECT * FROM PLAYER WHERE name = ? ";
        try {
            PlayerDto playerDto = jdbcTemplate.queryForObject(sql, actorRowMapper, name);
            return Optional.ofNullable(playerDto);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
