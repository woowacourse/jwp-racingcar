package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.dto.GameDto;
import racingcar.dto.GameResponse;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

class RacingCarServiceTest {

    private RacingCarService racingCarService;
    private GameDto gameDto;

    @BeforeEach
    void setUp() {
        GameDao gameDao = Mockito.mock(GameDao.class);
        CarDao carDao = Mockito.mock(CarDao.class);
        racingCarService = new RacingCarService(
                gameDao,
                carDao
        );
        int tryTimes = 5;
        int gameId = 1;
        gameDto = GameDto.of(List.of("조이", "밀리"), tryTimes);
        given(gameDao.insertGame(tryTimes)).willReturn(gameId);
        given(carDao.findCars(gameId)).willReturn(
                new ArrayList<>() {{
                    add(CarEntity.of(gameDto.getNames().get(0), 0));
                    add(CarEntity.of(gameDto.getNames().get(1), 0));
                }}
        );
        given(gameDao.findAll()).willReturn(List.of(GameEntity.of(gameId, tryTimes)));
    }

    @Test
    @DisplayName("게임을 실행한다.")
    void play() {
        GameResponse gameResponse = racingCarService.play(gameDto);

        assertAll(
                () -> assertThat(gameResponse.getWinners()).isEqualTo("조이,밀리"),
                () -> assertThat(gameResponse.getRacingCars().get(0).getName()).isEqualTo("조이"),
                () -> assertThat(gameResponse.getRacingCars().get(1).getName()).isEqualTo("밀리"),
                () -> assertThat(gameResponse.getRacingCars().get(0).getPosition()).isEqualTo(0),
                () -> assertThat(gameResponse.getRacingCars().get(1).getPosition()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("게임 이력을 조회한다.")
    void getGameResults() {
        List<GameResponse> gameResponses = racingCarService.getGameResults();

        assertAll(
                () -> assertThat(gameResponses.get(0).getWinners()).isEqualTo("조이,밀리"),
                () -> assertThat(gameResponses.get(0).getRacingCars().get(0).getName()).isEqualTo("조이"),
                () -> assertThat(gameResponses.get(0).getRacingCars().get(1).getName()).isEqualTo("밀리"),
                () -> assertThat(gameResponses.get(0).getRacingCars().get(0).getPosition()).isEqualTo(0),
                () -> assertThat(gameResponses.get(0).getRacingCars().get(1).getPosition()).isEqualTo(0)
        );
    }
}
