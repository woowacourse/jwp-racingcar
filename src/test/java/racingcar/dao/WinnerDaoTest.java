package racingcar.dao;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.GameInputDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql("/init.sql")
@JdbcTest
class WinnerDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Test
    void raceId에_매칭되는_playerId들을_반환한다() {
        RaceDao raceDao = new RaceDao(namedParameterJdbcTemplate);
        raceDao.insert(new GameInputDto("아벨1,스플릿1", "12"));
        
        PlayerDao playerDao = new PlayerDao(namedParameterJdbcTemplate);
        playerDao.insert(new CarDto(new Car("아벨1", 0)), 1);
        playerDao.insert(new CarDto(new Car("스플릿1", 0)), 1);
        
        WinnerDao winnerDao = new WinnerDao(namedParameterJdbcTemplate);
        winnerDao.insert(1, 1);
        winnerDao.insert(1, 2);
        
        List<Long> playerIds = winnerDao.findWinnerIdsByRaceId(1);
        assertThat(playerIds).containsExactly(1L, 2L);
    }
}