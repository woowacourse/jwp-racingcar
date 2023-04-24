package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.controller.dto.GameInformationDto;
import racingcar.dao.RacingCarDao;
import racingcar.dao.ResultDao;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
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
        GameInformationDto gameInformationDto = new GameInformationDto("roy, jamie", 10);
        racingCarService.runGame(gameInformationDto.getNames(), gameInformationDto.getCount());

        verify(resultDao, atLeastOnce()).insert(anyInt(), anyString());
        verify(racingCarDao, atLeastOnce()).insert(any(), anyLong());
    }
}
