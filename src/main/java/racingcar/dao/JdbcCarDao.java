package racingcar.dao;


import static racingcar.dao.ObjectMapper.carDTOMapper;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.entity.CarEntity;
import racingcar.dto.CarDTO;

@Repository
public class JdbcCarDao implements CarDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCarDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(final CarEntity carEntity) {
        final String sql = "INSERT INTO car(name, position, game_id, is_win) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, carEntity.getName(), carEntity.getPosition(), carEntity.getGameId(),
                carEntity.getIsWin());
    }

    @Override
    public List<CarDTO> selectAll(final int gameId) {
        final String sql = "SELECT name, position FROM car where game_id = ?";
        return jdbcTemplate.query(sql, carDTOMapper, gameId);
    }

    @Override
    public List<String> selectWinners(final int gameId){
        final String sql = "SELECT name FROM car where game_id = ? and is_win = TRUE";
        return jdbcTemplate.queryForList(sql, String.class, gameId);
    }
}
