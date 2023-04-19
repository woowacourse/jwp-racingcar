package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;
import racingcar.domain.GameInforamtionDto;
import racingcar.util.NumberGenerator;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@JdbcTest
class RacingCarServiceTest {

    @Mock
    ResultDao resultDao;
    @Mock
    RacingCarDao racingCarDao;
    @Mock
    NumberGenerator numberGenerator;
    @InjectMocks
    RacingCarService racingCarService;

    @Test
    @DisplayName("Dao 메서드가 제대로 호출되는지 확인")
    void insertGame() {
        when(numberGenerator.generateNumber()).thenReturn(8);
        String names = "roy, jamie";
        GameInforamtionDto gameInforamtionDto = new GameInforamtionDto(names, 10);

        racingCarService.play(gameInforamtionDto);

        verify(resultDao, times(1)).insert(anyInt(), anyString());
        verify(racingCarDao, times(2)).insert(any(), anyLong());
    }
}
