package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameResultDto;

@SpringBootTest
@Transactional
class GameServiceTest {

    @Autowired
    GameService gameService;

    @DisplayName("정상적으로 게임이 실행된다.")
    @Test
    void playGame() {
        final int playCount = 10;
        final List<String> names = List.of("리사", "한효주", "권나라");
        final String namesString = String.join(",", names);
        final GameInputDto inputDto = new GameInputDto(namesString, playCount);
        final GameResultDto gameResultDto = gameService.playGame(inputDto);
        final String[] winners = gameResultDto.getWinners().split(",");

        assertThat(gameResultDto.getPlayCount()).isEqualTo(playCount);
        assertThat(gameResultDto.getPlayers().size()).isEqualTo(names.size());
        for (final String winner : winners) {
            assertThat(names).contains(winner);
        }
    }
}
