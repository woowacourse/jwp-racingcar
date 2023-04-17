package racing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import racing.domain.Car;
import racing.domain.CarName;
import racing.domain.Cars;
import racing.repository.CarEntity;
import racing.repository.RacingGameDao;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {

    @InjectMocks
    private RacingGameService racingGameService;

    @Mock
    private RacingGameDao racingGameDao;


    @DisplayName("시도 횟수로 게임을 저장한다.")
    @Test
    void saveGameByCountTest() {
        when(racingGameDao.saveGame(anyInt())).thenReturn(0L);

        racingGameService.saveGameByCount(4);

        verify(racingGameDao, atLeastOnce()).saveGame(4);
    }

    @DisplayName("게임을 진행하면, 게임에 저장된 진행 횟수만큼 플레이한다.")
    @Test
    void playGameTest() {
        Cars testCars = Mockito.mock(Cars.class);
        int trialCount = 5;
        when(racingGameDao.findGameTrialById(anyLong())).thenReturn(trialCount);

        racingGameService.playGame(1L, testCars);

        verify(testCars, atMostOnce()).moveCarsByCount(5);
    }

    @DisplayName("가장 많이 전진한 자동차의 이름을 확인 할 수 있다.")
    @Test
    void getWinnersTest() {
        Cars testCars = Mockito.mock(Cars.class);
        when(testCars.getWinners()).thenReturn(List.of("CarA", "CarB"));

        String winners = racingGameService.filterWinnersToCarNames(testCars);

        assertThat(winners).isEqualTo("CarA,CarB");
    }

    @DisplayName("가장 많이 전진한 자동차의 이름을 확인 할 수 있다.")
    @Test
    void getAllResultsTest() {
        List<CarEntity> carEntities = List.of(
                CarEntity.of(1L, new Car(new CarName("CarA"), 3), true),
                CarEntity.of(1L, new Car(new CarName("CarB"), 2), false),
                CarEntity.of(2L, new Car(new CarName("CarC"), 1), false),
                CarEntity.of(2L, new Car(new CarName("CarD"), 2), true)
        );
        when(racingGameDao.findAllGameIdOrderByRecent()).thenReturn(List.of(1L, 2L));
        when(racingGameDao.findCarsInGame(any())).thenReturn(carEntities);

        List<Cars> allResults = racingGameService.getAllResults();

        assertThat(allResults).hasSize(2);

        Cars firstGameCars = allResults.get(0);
        assertThat(firstGameCars.getWinners()).contains("CarA");

        Cars secondGameCars = allResults.get(1);
        assertThat(secondGameCars.getWinners()).contains("CarD");
    }
}
