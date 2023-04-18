package racingcar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.domain.Car;
import racingcar.domain.TestNumberGenerator;
import racingcar.dto.request.GameRequestDto;
import racingcar.dto.response.GameResponseDto;
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
    public void playGameTest() {
        GameRequestDto requestDto = new GameRequestDto("ditoo,leo", 10);
        final String ditoo = requestDto.getNames().split(",")[0];
        final String leo = requestDto.getNames().split(",")[1];
        Game game = new Game(requestDto.getCount(), ditoo);
        given(gameRepository.save(any()))
                .willReturn(game);

        PlayerResult ditooResult = getDitooResult();
        PlayerResult leoResult = getLeoResult();

        given(playerResultRepository.save(any()))
                .willReturn(ditooResult);
        given(playerResultRepository.save(any()))
                .willReturn(leoResult);

        GameResponseDto gameResponseDto = gameService.playGame(requestDto);
        assertThat(gameResponseDto.getWinners()).isEqualTo(game.getWinners());
    }

    private PlayerResult getDitooResult() {
        Car ditooCar = new Car("ditoo");
        ditooCar.move(new TestNumberGenerator(7));
        ditooCar.move(new TestNumberGenerator(7));
        PlayerResult ditoo = new PlayerResult(
                ditooCar.getCarName().getName(), ditooCar.getCurrentPosition().getPosition(), 1);
        return new PlayerResult(1, ditoo);
    }

    private PlayerResult getLeoResult() {
        Car leoCar = new Car("leo");
        leoCar.move(new TestNumberGenerator(7));
        PlayerResult leo = new PlayerResult(
                leoCar.getCarName().getName(), leoCar.getCurrentPosition().getPosition(), 1);
        return new PlayerResult(1, leo);
    }
}
