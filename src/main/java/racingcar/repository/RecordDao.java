package racingcar.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import racingcar.model.RecordEntity;
import racingcar.model.Car;

import java.util.List;

@Repository
public class RecordDao {

    private final JdbcTemplate jdbcTemplate;

    public RecordDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(final long gameId, final boolean isWinner, final Car vehicle) {
        String sql = "insert into record(game_id, position, is_winner, player_name) values (?, ?, ?, ?)";

        jdbcTemplate.update(sql, gameId, vehicle.getDistance(), isWinner, vehicle.getName());
    }

    public List<RecordEntity> findAll() {
        String sql = "select * from record";

        return jdbcTemplate.query(sql, mapRow());
    }

    public RowMapper<RecordEntity> mapRow() {
        return (rs, rowNum) -> {
            RecordEntity recordDao = new RecordEntity();
            recordDao.setPlayerName(rs.getString(1));
            recordDao.setGameId(rs.getInt(2));
            recordDao.setPosition(rs.getInt(3));
            recordDao.setWinner(rs.getBoolean(4));

            return recordDao;
        };
    }
}
