package racingcar.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Repository;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;

@Repository
public class GameDao {
    private final JdbcTemplate jdbcTemplate;

    public GameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveGame(int trialCount, ResultDto resultDto) {
        long gameId = insertGame(trialCount);
        insertCar(resultDto, gameId);
        insertWinner(resultDto, gameId);
    }

    private long insertGame(int trialCount) {
        String sql = "INSERT INTO game (trialCount, date) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, trialCount);
            pst.setDate(2, Date.valueOf(LocalDate.now()));
            return pst;
        }, keyHolder);
        long gameId = keyHolder.getKey().longValue();
        return gameId;
    }

    private void insertCar(ResultDto resultDto, long gameId) {
        String sql2 = "INSERT INTO car(g_id, name, position) VALUES (?,?,?)";
        List<CarDto> racingCars = resultDto.getRacingCars();
        jdbcTemplate.batchUpdate(sql2, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, gameId);
                ps.setString(2, racingCars.get(i).getName());
                ps.setInt(3, racingCars.get(i).getPosition());
            }

            @Override
            public int getBatchSize() {
                return racingCars.size();
            }
        });
    }

    private void insertWinner(ResultDto resultDto, long gameId) {
        String sql3 = "INSERT INTO winner (g_id,winner) VALUES (?,?)";
        String[] winners = resultDto.getWinners().split(", ");
        jdbcTemplate.batchUpdate(sql3, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, gameId);
                ps.setString(2, winners[i]);
            }

            @Override
            public int getBatchSize() {
                return winners.length;
            }
        });
    }
}
