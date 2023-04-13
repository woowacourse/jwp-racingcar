package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.dto.RaceResultDto;
import racingcar.game.Game;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class PlayerDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private PlayerDao playerDao;
    
    @BeforeEach
    void setUp() {
        playerDao = new PlayerDao(namedParameterJdbcTemplate);
    }
    
    @Test
    void getWinnerCarIds() {
        playerDao.insertAll(new RaceResultDto(new Game("아벨,스플릿", "12")), 1);
        
    }
    
    @Test
    void getWinnerCardId() {
    }
}