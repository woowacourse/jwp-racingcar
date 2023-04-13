package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.GameInputDto;
import racingcar.dto.RaceResultDto;
import racingcar.game.Game;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class PlayerDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private PlayerDao playerDao;
    private Game game;
    
    @BeforeEach
    void setUp() {
        playerDao = new PlayerDao(namedParameterJdbcTemplate);
        game = new Game("아벨,스플릿,포비", "12");
        
        new RaceDao(namedParameterJdbcTemplate).insert(new GameInputDto("아벨,스플릿,포비", "12"));
        playerDao.insertAll(new RaceResultDto(game), 1);
    }
    
    @Test
    void getWinnerCarIds() {
        List<Integer> winnerCarIds = playerDao.getWinnerCarIds(1, new RaceResultDto(game));
        assertThat(winnerCarIds).containsExactly(1,2,3);
    }
    
    @Test
    void getWinnerCardId() {
        int carId = playerDao.getWinnerCarId(1, new CarDto(new Car("스플릿", 0)));
        assertThat(carId).isEqualTo(2);
    }
}