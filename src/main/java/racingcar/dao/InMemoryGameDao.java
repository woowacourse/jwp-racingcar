package racingcar.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.GamePlayResponseDto;

@Repository
public class InMemoryGameDao implements GameDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public InMemoryGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(int trialCount, String winners) {
        String sql = "insert into PLAY_RESULT (trial_Count, winners) values (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, trialCount);
            ps.setString(2, winners);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<GamePlayResponseDto> selectAll() {
        String sql = "SELECT p.id, p.winners, c.car_name, c.car_position\n"
                + "FROM PLAY_RESULT p JOIN CAR_RESULT c\n"
                + "ON p.id = c.play_result_id;";

        return jdbcTemplate.query(sql, getGamePlayResponseExtractor);
    }

    private final ResultSetExtractor<List<GamePlayResponseDto>> getGamePlayResponseExtractor = resultSet -> {
        final Map<Integer, GamePlayResponseDto> historyByGame = new HashMap<>();
        while (resultSet.next()) {
            final int gameId = resultSet.getInt("id");
            final String winners = resultSet.getString("winners");
            GamePlayResponseDto gameResult = getGameResult(historyByGame, gameId, winners);
            final List<CarDto> racingCars = gameResult.getRacingCars();
            racingCars.add(new CarDto(resultSet.getString("car_name"), resultSet.getInt("car_position")));
        }
        return new ArrayList<>(historyByGame.values());
    };

    private GamePlayResponseDto getGameResult(final Map<Integer, GamePlayResponseDto> resultByGameId,
                                              final int gameId,
                                              final String winners) {
        GamePlayResponseDto gameResult = resultByGameId.get(gameId);
        if (gameResult == null) {
            gameResult = new GamePlayResponseDto(winners);
            resultByGameId.put(gameId, gameResult);
        }
        return gameResult;
    }
}
