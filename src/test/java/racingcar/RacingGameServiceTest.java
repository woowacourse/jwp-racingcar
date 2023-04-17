package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyBoolean;
import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dto.CarRecordDto;
import racingcar.dao.CarRecordDao;
import racingcar.dto.RacingHistoryDto;
import racingcar.dao.RacingHistoryDao;
import racingcar.dto.ResultDto;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {

    @Mock
    private CarRecordDao carRecordDao;

    @Mock
    private RacingHistoryDao racingHistoryDao;

    @InjectMocks
    private RacingGameService racingGameService;

    @DisplayName("게임 결과를 저장한다.")
    @Test
    void saveRacingGameResult() {
        //given
        List<String> carNames = List.of("로지", "바론");
        given(racingHistoryDao.save(anyInt(), any())).willReturn(1L);
        given(carRecordDao.save(anyLong(), any(), anyBoolean())).willReturn(1L);
        //when
        ResultDto result = racingGameService.start(10, carNames);
        //then
        assertAll(
                () -> assertThat(result.getRacingCars()).hasSize(2),
                () -> assertThat(result.getWinners()).isNotEmpty()
        );
    }

    @DisplayName("전체 누적 게임 결과를 조회한다.")
    @Test
    void findAllRacingGameResult() {
        //given
        LocalDateTime time = LocalDateTime.of(2023, 4, 17, 15, 54, 55);
        given(racingHistoryDao.findAll()).willReturn(
                List.of(new RacingHistoryDto(1, 10, time), new RacingHistoryDto(2, 10, time)));
        given(carRecordDao.findAllByRacingHistoryId(anyLong())).willReturn(
                List.of(new CarRecordDto("Rosie", 5, true), new CarRecordDto("Baron", 3, false)));

        //when
        List<ResultDto> allGames = racingGameService.findAllGameHistories();

        //then
        assertAll(
                () -> assertThat(allGames).hasSize(2),
                () -> assertThat(allGames.get(0).getWinners()).isEqualTo("Rosie"),
                () -> assertThat(allGames.get(0).getRacingCars()).hasSize(2)
        );
    }

}
