package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.dto.CarNameDTO;
import racingcar.dto.CarNamePositionDTO;

import java.util.List;

@Repository
public class JdbcCarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(final String name, final int position, final Long gameId, final boolean isWin) {
        String sql = "INSERT INTO car(name, position, game_id, is_win) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, name, position, gameId, isWin);
    }

    @Override
    public int countRows() {
        final String sql = "select count(*) from car";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM car");
    }

    @Override
    public List<CarNameDTO> findWinners(final Long gameId) {
        final String sql = "select name from car where game_id = ? and is_win = true";
        return jdbcTemplate.query(sql, getCarNameDTORowMapper(), gameId);
    }

    private RowMapper<CarNameDTO> getCarNameDTORowMapper() {
        return (resultSet, rowNum) -> new CarNameDTO(resultSet.getString("name"));
    }

    @Override
    public List<CarNamePositionDTO> findAllCarNamesAndPositions(final Long gameId) {
        final String sql = "select name, position from car where game_id = ?";
        return jdbcTemplate.query(sql, getCarNamePositionDTORowMapper(), gameId);
    }

    private RowMapper<CarNamePositionDTO> getCarNamePositionDTORowMapper() {
        return (resultSet, rowNum) -> new CarNamePositionDTO(resultSet.getString("name"), resultSet.getInt("position"));
    }
}
