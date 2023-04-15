package racingcar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.domain.Car;
import racingcar.domain.TestNumberGenerator;
import racingcar.dto.request.GameResultDto;
import racingcar.dto.request.GameSaveDto;
import racingcar.dto.request.PlayerResultSaveDto;
import racingcar.dto.response.GameResponseDto;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;
import racingcar.repository.GameRepository;
import racingcar.repository.PlayerResultRepository;

import java.util.List;

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
    public void playGameTest() {
        GameSaveDto gameSaveDto = new GameSaveDto("ditoo", 10);
        Game game = new Game(1, gameSaveDto);
        given(gameRepository.createGame(any()))
                .willReturn(game);

        Car ditooCar = new Car("ditoo");
        PlayerResult ditooResult = getDitooResult(ditooCar);

        Car leoCar = new Car("leo");
        PlayerResult leoResult = getLeoResult(leoCar);

        given(playerResultRepository.savePlayerResult(any()))
                .willReturn(ditooResult);
        given(playerResultRepository.savePlayerResult(any()))
                .willReturn(leoResult);

        GameResultDto gameResultDto = GameResultDto.of(List.of("ditoo"), 2, List.of(ditooCar, leoCar));

        GameResponseDto gameResponseDto = gameService.playGame(gameResultDto);
        assertThat(gameResponseDto.getWinners()).isEqualTo(gameResultDto.getWinners());
    }

    private PlayerResult getDitooResult(Car ditooCar) {
        ditooCar.move(new TestNumberGenerator(7).generate());
        ditooCar.move(new TestNumberGenerator(7).generate());
        PlayerResultSaveDto ditoo = new PlayerResultSaveDto(1, ditooCar);
        return new PlayerResult(1, ditoo);
    }

    private PlayerResult getLeoResult(Car leoCar) {
        leoCar.move(new TestNumberGenerator(7).generate());
        PlayerResultSaveDto leo = new PlayerResultSaveDto(1, leoCar);
        return new PlayerResult(1, leo);
    }
}
