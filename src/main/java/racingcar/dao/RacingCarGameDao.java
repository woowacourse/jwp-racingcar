package racingcar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.Game;
import racingcar.dto.PlayerDto;
import racingcar.dto.ResultResponseDto;

@Component
public class RacingCarGameDao {

    private final JdbcTemplate jdbcTemplate;

    public RacingCarGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertGameWithKeyHolder(Game game) {
        String sql = "INSERT INTO game(play_count, winners) VALUES(?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, game.getPlayCount());
            ps.setString(2, game.getWinners());
            return ps;
        }, keyHolder);

        return Long.valueOf(String.valueOf(keyHolder.getKeys().get("game_id")));
    }

    public List<ResultResponseDto> findAll() {
        String sql = "SELECT g.winners, g.game_id, p.name, p.position FROM GAME as g "
            + "join player as p "
            + "on g.game_id = p.game_id ";
        return jdbcTemplate.query(sql, rs -> {
                Map<Long, ResultResponseDto> history = new HashMap<>();
                while (rs.next()) {
                    long gameId = rs.getLong("game_id");
                    putData(rs, history, gameId);
                }
                return new ArrayList<>(history.values());
            }
        );
    }

    private void putData(final ResultSet rs, final Map<Long, ResultResponseDto> history, final long gameId) throws SQLException {
        if (history.containsKey(gameId)) {
            ResultResponseDto resultResponseDto = history.get(gameId);
            resultResponseDto.getRacingCars().add(new PlayerDto(rs.getString("name"), rs.getInt("position")));
            return;
        }
        List<PlayerDto> players = new ArrayList<>();
        players.add(new PlayerDto(rs.getString("name"), rs.getInt("position")));
        history.put(gameId, new ResultResponseDto(rs.getString("winners"), players));
    }
}
