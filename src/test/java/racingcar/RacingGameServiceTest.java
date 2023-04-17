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
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.RacingGameService;
import racingcar.domain.game.RandomNumberGenerator;
import racingcar.dto.RacingGameDto;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {

    @Mock
    private RacingCarRecordDao racingCarRecordDao;

    @Mock
    private RacingGameHistoryDao racingGameHistoryDao;

    private RacingGameService racingGameService;

    @BeforeEach
    void setUp() {
        racingGameService = new RacingGameService(racingGameHistoryDao, racingCarRecordDao, new RandomNumberGenerator());
    }

    @DisplayName("게임 결과를 저장한다.")
    @Test
    void insertRacingGameResult() {
        //given
        List<String> carNames = List.of("로지", "바론");
        given(racingGameHistoryDao.insert(anyInt(), any())).willReturn(1L);
        given(racingCarRecordDao.insert(anyLong(), any(), anyBoolean())).willReturn(1L);
        //when
        RacingGameDto result = racingGameService.play(10, carNames);
        //then
        assertAll(
                () -> assertThat(result.getRacingCars()).hasSize(2),
                () -> assertThat(result.getWinnerNames()).isNotEmpty()
        );
    }

}
