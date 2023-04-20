package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dto.GameInforamtionDto;
import racingcar.dto.GameResultDto;
import racingcar.dto.RacingCarDto;
import racingcar.repository.RacingCarDao;
import racingcar.repository.ResultDao;
import racingcar.util.RandomNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        String names = "roy, jamie";
        GameInforamtionDto gameInforamtionDto = new GameInforamtionDto(names, 10);

        racingCarService.play(gameInforamtionDto, new RandomNumberGenerator());

        verify(resultDao, times(1)).insert(anyInt(), anyString());
        verify(racingCarDao, times(2)).insert(any(), anyLong());
    }

    @Test
    @DisplayName("게임 결과 목록이 제대로 반환되는지 확인")
    void findAllGame() {
        when(resultDao.findAllId()).thenReturn(List.of(1L));
        when(resultDao.findWinnerBy(1)).thenReturn("test1");
        when(racingCarDao.findBy(1)).thenReturn(
                List.of(new RacingCarDto("test1", 8),
                new RacingCarDto("test2", 4)));

        List<GameResultDto> gameResult = racingCarService.findAllGame();
        List<String> carNames = gameResult.get(0).getRacingCars().stream()
                                          .map(RacingCarDto::getName)
                                          .collect(Collectors.toList());
        List<Integer> positions = gameResult.get(0).getRacingCars().stream()
                                          .map(RacingCarDto::getPosition)
                                          .collect(Collectors.toList());

        assertAll(
                () -> assertThat(gameResult.get(0).getWinners()).isEqualTo("test1"),
                () -> assertThat(carNames).containsExactly("test1", "test2"),
                () -> assertThat(positions).containsExactly(8, 4)
        );
    }
}
