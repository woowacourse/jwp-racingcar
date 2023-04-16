package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyBoolean;
import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.CarRecordDao;
import racingcar.dao.RacingHistoryDao;
import racingcar.domain.RacingGameService;
import racingcar.domain.RandomNumberGenerator;
import racingcar.dto.ResultDto;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {

    @Mock
    private CarRecordDao carRecordDao;

    @Mock
    private RacingHistoryDao racingHistoryDao;

    private RacingGameService racingGameService;

    @BeforeEach
    void setUp() {
        racingGameService = new RacingGameService(racingHistoryDao, carRecordDao, new RandomNumberGenerator());
    }

    @DisplayName("게임 결과를 저장한다.")
    @Test
    void insertRacingGameResult() {
        //given
        List<String> carNames = List.of("로지", "바론");
        given(racingHistoryDao.insert(anyInt(), any())).willReturn(1L);
        given(carRecordDao.insert(anyLong(), any(), anyBoolean())).willReturn(1L);
        //when
        ResultDto result = racingGameService.start(10, carNames);
        //then
        assertAll(
                () -> assertThat(result.getRacingCars()).hasSize(2),
                () -> assertThat(result.getWinners()).isNotEmpty()
        );
    }

}
