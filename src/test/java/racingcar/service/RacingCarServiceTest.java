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
import racingcar.entity.RacingCar;
import racingcar.entity.Result;
import racingcar.repository.RacingCarDao;
import racingcar.repository.ResultDao;
import racingcar.util.RandomNumberGenerator;

import java.time.LocalDateTime;
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
        when(resultDao.findAll())
                .thenReturn(List.of(new Result(1L, 5, "test1", LocalDateTime.now())));
        when(racingCarDao.findBy(1))
                .thenReturn(
                        List.of(new RacingCar(1L, "test1", 8, 1L),
                                new RacingCar(2L, "test2", 4, 1L))
                );

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
