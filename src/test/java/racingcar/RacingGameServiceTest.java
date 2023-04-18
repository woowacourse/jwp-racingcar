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
import java.util.stream.Collectors;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.RacingCarRecord;
import racingcar.dao.RacingCarRecordDao;
import racingcar.dao.RacingGameHistory;
import racingcar.dao.RacingGameHistoryDao;
import racingcar.domain.RacingGameService;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.RacingGame;
import racingcar.domain.game.RandomNumberGenerator;
import racingcar.dto.RacingCarDto;
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

    @DisplayName("게임 이력을 조회할 수 있다.")
    @Test
    void readRacingGameResult() {
        //given
        Long historyId = 1L;
        given(racingGameHistoryDao.selectAll()).willReturn(
                List.of(new RacingGameHistory(historyId, 3, LocalDateTime.now()))
        );
        given(racingCarRecordDao.findByHistoryId(historyId)).willReturn(
                List.of(
                        new RacingCarRecord(1L, "이름", 2, true, historyId),
                        new RacingCarRecord(2L, "이름2", 1, false, historyId),
                        new RacingCarRecord(3L, "이름3", 1, false, historyId)
                )
        );

        //when
        List<RacingGameDto> racingGames = racingGameService.readGameHistory();
        //then
        RacingGameDto readRacingGame = racingGames.get(0);
        List<RacingCarDto> racingCarDtos = readRacingGame.getRacingCars();
        List<String> names = racingCarDtos.stream().map(RacingCarDto::getName).collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("이름", "이름2", "이름3");

    }
}
