package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.service.dto.GameRequestDto;
import racingcar.service.dto.GameResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    GameService gameService;

    @Test
    @DisplayName("게임 저장 및 플레이어 결과 저장 테스트")
    public void playGameTest() {
        // given
        GameRequestDto requestDto = new GameRequestDto("ditoo,leo", 10);

        // when
        GameResponseDto responseDto = gameService.createGameResult(requestDto);

        // then
        assertAll(
                () -> assertThat(responseDto.getRacingCars()).hasSize(2),
                () -> assertThat(responseDto.getRacingCars().get(0).getName()).isEqualTo("ditoo"),
                () -> assertThat(responseDto.getRacingCars().get(1).getName()).isEqualTo("leo"),
                () -> assertThat(responseDto.getWinners()).containsAnyOf("ditoo", "leo", "ditoo,leo")
        );
    }

    @Test
    @DisplayName("전체 게임 조회 테스트")
    public void getAllTest() {
        // given
        GameRequestDto requestDto1 = new GameRequestDto("ditoo,leo", 10);
        GameRequestDto requestDto2 = new GameRequestDto("디투,홍실,에단,블랙캣", 5);
        gameService.createGameResult(requestDto1);
        gameService.createGameResult(requestDto2);

        // when
        List<GameResponseDto> response = gameService.getAll();

        // then
        assertAll(
                () -> assertThat(response).hasSize(2),
                () -> assertThat(response.get(0).getRacingCars()).hasSize(2),
                () -> assertThat(response.get(1).getRacingCars()).hasSize(4),
                () -> assertThat(response.get(0).getRacingCars().get(0).getName()).isEqualTo("ditoo"),
                () -> assertThat(response.get(0).getRacingCars().get(1).getName()).isEqualTo("leo"),
                () -> assertThat(response.get(1).getRacingCars().get(0).getName()).isEqualTo("디투"),
                () -> assertThat(response.get(1).getRacingCars().get(1).getName()).isEqualTo("홍실"),
                () -> assertThat(response.get(1).getRacingCars().get(2).getName()).isEqualTo("에단"),
                () -> assertThat(response.get(1).getRacingCars().get(3).getName()).isEqualTo("블랙캣")
        );
    }
}
