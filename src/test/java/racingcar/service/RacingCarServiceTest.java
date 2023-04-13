package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.Cars;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class RacingCarServiceTest {

    @Mock
    ResultDao resultDao;
    @Mock
    RacingCarDao racingCarDao;
    @InjectMocks
    RacingCarService racingCarService;

    @Test
    @DisplayName("Dao 메서드가 제대로 호출되는지 확인")
    void insertGame() {
        Cars cars = new Cars("roy, jamie");
        racingCarService.insertGame(10, cars);

        verify(resultDao, times(1)).insert(anyInt(), anyString());
        verify(racingCarDao, times(2)).insert(any(), anyLong());
    }
}
