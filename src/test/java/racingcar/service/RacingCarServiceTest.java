package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.controller.dto.GameStartRequest;
import racingcar.controller.dto.CarStateResponse;
import racingcar.controller.dto.GameResultReponse;
import racingcar.dao.GameJdbcTemplateDao;
import racingcar.dao.ParticipantJdbcTemplateDao;
import racingcar.dao.PlayerJdbcTemplateDao;
import racingcar.dao.entity.GameEntity;
import racingcar.dao.entity.ParticipantEntity;
import racingcar.dao.entity.PlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    @Mock
    private GameJdbcTemplateDao gameJdbcTemplateDao;
    @Mock
    private PlayerJdbcTemplateDao playerJdbcTemplateDao;
    @Mock
    private ParticipantJdbcTemplateDao participantJdbcTemplateDao;
    @InjectMocks
    private RacingCarService racingCarService;

    @DisplayName("모든 이력을 반환한다.")
    @Test
    void searchAllGame() {
        //mocking -> DAO에서 조회되는 값 제어
        Mockito.when(gameJdbcTemplateDao.findAll()).thenReturn(List.of(
                new GameEntity(1L, 10),
                new GameEntity(2L, 20)));
        Mockito.when(playerJdbcTemplateDao.findAll()).thenReturn(List.of(
                new PlayerEntity(1L, "망고"),
                new PlayerEntity(2L, "루카"),
                new PlayerEntity(3L, "소니"),
                new PlayerEntity(4L, "현구막")));

        Mockito.when(participantJdbcTemplateDao.findAll()).thenReturn(List.of(
                new ParticipantEntity(1L, 1L, 10, true),
                new ParticipantEntity(1L, 2L, 5, false),
                new ParticipantEntity(2L, 3L, 20, true),
                new ParticipantEntity(2L, 4L, 15, false)));

        //when
        List<GameResultReponse> gameResultResponses = racingCarService.searchAllGame();
        GameResultReponse mangoAndLuca = gameResultResponses.get(0);
        GameResultReponse sonyAndHyeon9mak = gameResultResponses.get(1);
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
        GameStartRequest gameStartRequest = new GameStartRequest("망고,루카,소니,현구막", 10);
        //mocking
        Mockito.when(gameJdbcTemplateDao.save(Mockito.anyInt())).thenReturn(1L);
        Mockito.when(playerJdbcTemplateDao.save(Mockito.any())).thenReturn(1L, 2L, 3L, 4L);
        Mockito.doNothing().when(participantJdbcTemplateDao).save(Mockito.any());
        //when
        GameResultReponse gameResultReponse = racingCarService.playGame(gameStartRequest);
        //then
        List<String> names = gameResultReponse.getRacingCars().stream()
                .map(CarStateResponse::getName)
                .collect(Collectors.toList());
        assertThat(gameResultReponse.getWinners()).isNotNull(); //어떻게 돌아가는지는 이미 도메인테스트에서 끝남
        assertThat(names).containsExactly("망고", "루카", "소니", "현구막");
    }
}

