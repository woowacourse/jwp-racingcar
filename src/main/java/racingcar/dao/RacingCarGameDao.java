package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import racingcar.dao.entity.Game;
import racingcar.dao.entity.Player;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

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

    public void insertPlayer(Player player) {
        String sql = "INSERT INTO player(name, position, game_id) VALUES(?, ?, ?)";

        jdbcTemplate.update(sql, player.getName(), player.getPosition(), player.getGameId());
    }

    public void updateGame(Game game) {
        String sql = "UPDATE game SET play_count = ?, winners = ? WHERE game_id = ?";

        jdbcTemplate.update(sql, game.getPlayCount(), game.getWinners(), game.getGameId());
    }

    public List<Game> queryAllGames() {
        String sql = "SELECT * from game";

        RowMapper<Game> gameRowMapper = (rs, rowNum) -> new Game(
                rs.getLong("game_id"),
                rs.getInt("play_count"),
                rs.getString("winners")
        );

        return jdbcTemplate.query(sql, gameRowMapper);
    }

    public List<Player> findPlayersByGameId(Long gameId) {
        String sql = "SELECT * from player WHERE game_id = ?";

        RowMapper<Player> playerRowMapper = (rs, rowNum) -> new Player(
                rs.getLong("player_id"),
                rs.getString("name"),
                rs.getInt("position"),
                rs.getLong("game_id")
        );

        return jdbcTemplate.query(sql, playerRowMapper, gameId);
    }
}
