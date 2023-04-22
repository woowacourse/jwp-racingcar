package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.domain.Cars;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.GameDao;
import racingcar.repository.PlayerResultDao;
import racingcar.service.dto.GameRequestDto;
import racingcar.service.dto.GameResponseDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    GameDao gameDao;

    @Mock
    PlayerResultDao playerResultDao;

    @InjectMocks
    GameService gameService;

    @Test
    @DisplayName("게임 저장 및 플레이어 결과 저장 테스트")
    public void playGameTest() {
        // given
        GameRequestDto requestDto = new GameRequestDto("ditoo,leo", 10);
        Game game = new Game(requestDto.getCount(), "ditoo");
        given(gameDao.save(any()))
                .willReturn(game);
        given(playerResultDao.save(any()))
                .willReturn(new PlayerResult(1, "ditoo", 8, game.getId()));
        given(playerResultDao.save(any()))
                .willReturn(new PlayerResult(2,"leo", 6, game.getId()));

        Cars cars = new Cars(requestDto.getNames());
        GameResponseDto responseExpected = GameResponseDto.createByDomain(game.getWinners(), cars);

        // when
        GameResponseDto responseActually;
        try (MockedStatic<GameService> utilities = Mockito.mockStatic(GameService.class)) {
            utilities.when(() -> GameService.race(any(), any(), any()))
                    .thenReturn(responseExpected);
            responseActually = gameService.createGameResult(requestDto);
        }

        // then
        assertThat(responseActually.getWinners()).isEqualTo(responseExpected.getWinners());
    }

    @Test
    @DisplayName("전체 게임 조회 테스트")
    public void getAllTest() {
//        // given
//        List<GetPlayerResultQueryResponseDto> queryResponses = new ArrayList<>();
//        queryResponses.add(new GetPlayerResultQueryResponseDto(1,"디투", "디투", 8));
//        queryResponses.add(new GetPlayerResultQueryResponseDto(1,"디투", "레오", 6));
//        queryResponses.add(new GetPlayerResultQueryResponseDto(2,"디투,홍실", "디투", 8));
//        queryResponses.add(new GetPlayerResultQueryResponseDto(2,"디투,홍실", "블랙캣", 6));
//        queryResponses.add(new GetPlayerResultQueryResponseDto(2,"디투,홍실", "홍실", 8));
//        queryResponses.add(new GetPlayerResultQueryResponseDto(2,"디투,홍실", "에단", 7));
//        given(playerResultDao.findByGameId())
//                .willReturn(queryResponses);
//
//        // when
//        List<GameResponseDto> gameResponseDtos = gameService.getAll();
//
//        // then
//        assertThat(gameResponseDtos.get(0).getWinners()).isEqualTo(queryResponses.get(0).getWinners());
//        assertThat(gameResponseDtos.get(0).getRacingCars().size()).isEqualTo(2);
//        assertThat(gameResponseDtos.get(1).getWinners()).isEqualTo(queryResponses.get(3).getWinners());
//        assertThat(gameResponseDtos.get(1).getRacingCars().size()).isEqualTo(4);
    }
}
