package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameInputDto;
import racingcar.dto.GameResultDto;

@SpringBootTest
@Transactional
class GameServiceTest {

    private static final String NAME_DELIMITER = ",";

    @Autowired
    GameService gameService;

    private static Stream<Arguments> provideInputs() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new GameInputDto("리사", 1),
                                new GameInputDto("리사,토미,포비", 99)
                        )
                ),
                Arguments.of(
                        List.of(
                                new GameInputDto("리사,토미", 2),
                                new GameInputDto("리사,토미,포비,네오", 100)
                        )
                )
        );
    }

    @DisplayName("정상적으로 게임이 실행된다.")
    @Test
    void playGame() {
        final int playCount = 10;
        final List<String> names = List.of("리사", "한효주", "권나라");
        final String namesString = String.join(NAME_DELIMITER, names);
        final GameInputDto inputDto = new GameInputDto(namesString, playCount);
        final GameResultDto gameResultDto = gameService.playGame(inputDto);
        final String[] winners = gameResultDto.getWinners().split(NAME_DELIMITER);

        assertThat(gameResultDto.getPlayCount()).isEqualTo(playCount);
        assertThat(gameResultDto.getPlayers()).hasSize(names.size());
        for (final String winner : winners) {
            assertThat(names).contains(winner);
        }
    }

    @DisplayName("저장된 데이터를 정상적으로 불러온다.")
    @ParameterizedTest()
    @MethodSource("provideInputs")
    void fetchHistory(final List<GameInputDto> inputs) {
        for (final GameInputDto input : inputs) {
            gameService.playGame(input);
        }

        final List<GameResultDto> results = gameService.fetchHistory();
        for (final GameResultDto result : results) {
            System.out.println(result.getPlayCount());
        }

        assertThat(results).hasSize(inputs.size());
        for (int i = 0; i < inputs.size(); i++) {
            final GameResultDto result = results.get(i);
            final GameInputDto input = inputs.get(i);
            final int namesSize = input.getNames().split(NAME_DELIMITER).length;
            assertThat(result.getPlayCount()).isEqualTo(input.getPlayCount());
            assertThat(result.getPlayers()).hasSize(namesSize);
            for (final String winner : result.getWinners().split(NAME_DELIMITER)) {
                assertThat(input.getNames()).contains(winner);
            }
        }
    }
}
