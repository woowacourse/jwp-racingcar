package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
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
import racingcar.dto.RacingResultRequestDto;
import racingcar.game.Game;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql("/data.sql")
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
        playerDao.insertAll(new RacingResultRequestDto(game), 1);
    }
    
    @Test
    void raceId에_매칭되는_모든_WinnerId를_반환한다() {
        List<Integer> winnerCarIds = playerDao.findWinnerCarIds(1, new RacingResultRequestDto(game));
        assertThat(winnerCarIds).containsExactly(1, 2, 3);
    }
    
    @Test
    void raceId와_CarDto에_매칭되는_WinnerId를_반환한다() {
        int carId = playerDao.findWinnerCarId(1, new CarDto(new Car("스플릿", 0)));
        assertThat(carId).isEqualTo(2);
    }
    
    @Test
    void Player의_id들에_매칭되는_CarDto들을_반환한다() {
        List<CarDto> carDtos = playerDao.findByIds(List.of(1L, 2L));
        List<String> playerNames = carDtos.stream()
                .map(CarDto::getName)
                .collect(Collectors.toUnmodifiableList());
        assertThat(playerNames).containsExactly("아벨", "스플릿");
    }
    
    @Test
    void Race의_id들에_매칭되는_CarDto들을_반환한다() {
        List<CarDto> carDtos = playerDao.findByRaceIds(1L);
        List<String> names = carDtos.stream()
                .map(CarDto::getName)
                .collect(Collectors.toUnmodifiableList());
        
        List<Integer> positions = carDtos.stream()
                .map(CarDto::getPosition)
                .collect(Collectors.toUnmodifiableList());
        
        assertAll(
                () -> assertThat(names).containsExactly("아벨", "스플릿", "포비"),
                () -> assertThat(positions).containsExactly(0, 0, 0)
        );
    }
}