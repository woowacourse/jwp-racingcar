package racingcar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.entity.GameHistoryEntity;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Long insertWithKeyHolder(int trialCount, List<String> winners) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String updateSql = "insert into PLAY_RESULT (trialcount, winners) values (?, ?)";

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement preparedStatement = con.prepareStatement(updateSql, new String[]{"id"});
            preparedStatement.setInt(1, trialCount);
            preparedStatement.setString(2, String.join(",", winners));
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<GameHistoryEntity> findGameHistories() {
        final String querySql = "select pr.id, winners, player_name, player_position "
                + "from play_result as pr "
                + "left join racing_car as rc "
                + "on pr.id = rc.game_id ";
        return jdbcTemplate.query(querySql, PlayResultDao::mapGameHistoryRow);
    }

    private static GameHistoryEntity mapGameHistoryRow(ResultSet rs, int rowNum) throws SQLException {
        final long id = rs.getLong("pr.id");
        final String winners = rs.getString("winners");
        final String name = rs.getString("playerName");
        final int position = rs.getInt("player_position");
        return new GameHistoryEntity(id, winners, name, position);
    }
}
