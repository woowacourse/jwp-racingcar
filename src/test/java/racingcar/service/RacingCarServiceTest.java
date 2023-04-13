package racingcar.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.PlayResultDao;
import racingcar.dao.RacingCarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    private static final long gameId = 1L;

    @Mock
    private PlayResultDao playResultDao;
    @Mock
    private RacingCarDao racingCarDao;
    @InjectMocks
    private RacingCarService racingCarService;

    @Test
    public void saveTest() {
        final List<Car> carList = List.of(new Car("a", 1), new Car("b", 2));
        final int tryCountValue = 5;
        final Cars cars = new Cars(carList);
        final TryCount tryCount = new TryCount(tryCountValue);

        when(playResultDao.insertWithKeyHolder(anyInt(), anyList()))
                .thenReturn(gameId);
        doNothing().when(racingCarDao)
                .insert(anyLong(), anyString(), anyInt());

        racingCarService.saveGameResult(cars, tryCount);

        verify(playResultDao).insertWithKeyHolder(tryCount.getValue(), cars.getWinner());
        verify(racingCarDao).insert(gameId, "a", 1);
        verify(racingCarDao).insert(gameId, "b", 2);
    }
}
