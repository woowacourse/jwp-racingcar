package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.GameDao;
import racingcar.dao.ParticipantDao;
import racingcar.dao.PlayerDao;
import racingcar.dto.NamesAndCountRequest;
import racingcar.dto.RacingCarResponse;
import racingcar.dto.ResultResponse;
import racingcar.entity.GameEntity;
import racingcar.entity.ParticipantEntity;
import racingcar.entity.PlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    @Mock
    private GameDao gameDao;
    @Mock
    private PlayerDao playerDao;
    @Mock
    private ParticipantDao participantDao;
    @InjectMocks
    private RacingCarService racingCarService;

    @DisplayName("모든 이력을 반환한다.")
    @Test
    void searchAllGame() {
        //mocking -> DAO에서 조회되는 값 제어
        Mockito.when(gameDao.findAll()).thenReturn(List.of(
                new GameEntity(1L, 10),
                new GameEntity(2L, 20)));
        Mockito.when(playerDao.findAll()).thenReturn(List.of(
                new PlayerEntity(1L, "망고"),
                new PlayerEntity(2L, "루카"),
                new PlayerEntity(3L, "소니"),
                new PlayerEntity(4L, "현구막")));

        Mockito.when(participantDao.findAll()).thenReturn(List.of(
                new ParticipantEntity(1L, 1L, 10, true),
                new ParticipantEntity(1L, 2L, 5, false),
                new ParticipantEntity(2L, 3L, 20, true),
                new ParticipantEntity(2L, 4L, 15, false)));

        //when
        List<ResultResponse> resultResponses = racingCarService.searchAllGame();
        ResultResponse mangoAndLuca = resultResponses.get(0);
        ResultResponse sonyAndHyeon9mak = resultResponses.get(1);
        //then
        assertThat(mangoAndLuca.getWinners()).isEqualTo("망고");
        assertThat(mangoAndLuca.getRacingCars().get(0).getName()).isEqualTo("망고");
        assertThat(mangoAndLuca.getRacingCars().get(0).getPosition()).isEqualTo(10);
        assertThat(mangoAndLuca.getRacingCars().get(1).getName()).isEqualTo("루카");
        assertThat(mangoAndLuca.getRacingCars().get(1).getPosition()).isEqualTo(5);
        assertThat(sonyAndHyeon9mak.getWinners()).isEqualTo("소니");
        assertThat(sonyAndHyeon9mak.getRacingCars().get(0).getName()).isEqualTo("소니");
        assertThat(sonyAndHyeon9mak.getRacingCars().get(0).getPosition()).isEqualTo(20);
        assertThat(sonyAndHyeon9mak.getRacingCars().get(1).getName()).isEqualTo("현구막");
        assertThat(sonyAndHyeon9mak.getRacingCars().get(1).getPosition()).isEqualTo(15);
    }

    // TODO: 이 테스트에서 호출되는 서비스 안에서 도메인 객체를 만들어 실행시키는데, 그 객체는 제어할 수 없나?
    @DisplayName("이름들과 시도 횟수를 입력받아 결과를 반환한다.")
    @Test
    void playGame() {
        //given
        NamesAndCountRequest namesAndCountRequest = new NamesAndCountRequest("망고,루카,소니,현구막", 10);
        //mocking
        Mockito.when(gameDao.save(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(playerDao.save(Mockito.any())).thenReturn(1L, 2L, 3L, 4L);
        Mockito.doNothing().when(participantDao).save(Mockito.any());
        //when
        ResultResponse resultResponse = racingCarService.playGame(namesAndCountRequest);
        //then
        List<String> names = resultResponse.getRacingCars().stream()
                .map(RacingCarResponse::getName)
                .collect(Collectors.toList());
        assertThat(resultResponse.getWinners()).isNotNull(); //어떻게 돌아가는지는 이미 도메인테스트에서 끝남
        assertThat(names).containsExactly("망고", "루카", "소니", "현구막");
    }
}

