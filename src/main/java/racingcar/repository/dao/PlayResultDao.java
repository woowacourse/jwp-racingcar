package racingcar.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.service.dto.GameHistoryDto;

@Repository
public class PlayResultDao {

    private static final String SPLITTER = ",";

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertWithKeyHolder(final List<String> winners) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String updateSql = "insert into PLAY_RESULT (winners) values (?)";

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement preparedStatement = con.prepareStatement(updateSql, new String[]{"id"});
            preparedStatement.setString(1, String.join(SPLITTER, winners));
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<GameHistoryDto> findGameHistories() {
        final String querySql = "select pr.id, winners, player_name, player_position "
                + "from play_result as pr "
                + "left join racing_car as rc "
                + "on pr.id = rc.game_id ";
        return jdbcTemplate.query(querySql, PlayResultDao::mapGameHistoryRow);
    }

    private static GameHistoryDto mapGameHistoryRow(ResultSet rs, int rowNum) throws SQLException {
        final long id = rs.getLong("id");
        final List<String> winners = Arrays.stream(rs.getString("winners").split(SPLITTER))
                .collect(Collectors.toUnmodifiableList());
        final String name = rs.getString("player_name");
        final int position = rs.getInt("player_position");
        return new GameHistoryDto(id, winners, name, position);
    }
}
