package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.service.dto.GameRequestDto;
import racingcar.service.dto.GameResponseDto;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.GameRepository;
import racingcar.repository.PlayerResultRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    PlayerResultRepository playerResultRepository;

    @InjectMocks
    GameService gameService;

    @Test
    @DisplayName("게임 저장 및 플레이어 결과 저장 테스트")
    public void playGameTest() {
        // given
        GameRequestDto requestDto = new GameRequestDto("ditoo,leo", 10);
        Game game = new Game(requestDto.getCount(), "ditoo");
        given(gameRepository.save(any()))
                .willReturn(game);
        given(playerResultRepository.save(any()))
                .willReturn(new PlayerResult(1, new PlayerResult("ditoo", 5, 1)));
        given(playerResultRepository.save(any()))
                .willReturn(new PlayerResult(2, new PlayerResult("leo", 3, 1)));

        // when
        GameResponseDto gameResponseDto = gameService.playGame(requestDto);

        // then
        assertThat(gameResponseDto.getWinners()).isEqualTo(game.getWinners());
    }
}
