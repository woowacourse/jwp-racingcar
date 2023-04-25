package racingcar.service;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    @InjectMocks
    private RacingCarService service;

    @Mock
    private GameDao gameDao;

    @Mock
    private CarDao carDao;

    @Test
    @DisplayName("자동차들의 이름과 시도 횟수를 입력하면 전체 결과를 반환한다.")
    void play() {
        when(gameDao.insertGame(20)).thenReturn(1);

        ResultDto result = service.play(List.of("조이", "밀리"), 20);
        assertThat(result.getCars())
                .extracting(CarDto::getName)
                .containsExactly("조이", "밀리");
    }

    @Test
    @DisplayName("전체 게임 이력을 조회한다.")
    void findAllResults() {
        when(gameDao.findAllGamesId()).thenReturn(List.of(1, 2));
        when(carDao.findWinners(1)).thenReturn(List.of(new Car("조이", 2)));
        when(carDao.findWinners(2)).thenReturn(List.of(new Car("밀리", 2)));
        when(carDao.findCars(1)).thenReturn(List.of(new Car("밀리", 1), new Car("조이", 2)));
        when(carDao.findCars(1)).thenReturn(List.of(new Car("밀리", 2), new Car("조이", 1)));

        assertThat(service.findAllResults())
                .hasSize(2)
                .extracting(ResultDto::getWinners)
                .hasSize(2);
    }
}
