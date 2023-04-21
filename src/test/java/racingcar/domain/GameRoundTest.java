package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class GameRoundTest {

    @ParameterizedTest(name = "게임 라운드 생성 테스트 : totalRound = {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void createGameRoundTest(int totalRound) {
        GameRound gameRound = new GameRound(totalRound);
        assertThat(gameRound).isNotNull();
    }

    @ParameterizedTest(name = "게임 진행 가능한 라운드인지 확인 테스트 : totalRound = {0}, executeTime = {1}, expectedIsEnd = {2}")
    @CsvSource(value = {"1:1:false", "2:2:false", "10:1:true"}, delimiterString = ":")
    void isEndRoundTest(int totalRound, int executeTimes, boolean expectedIsEnd) {
        GameRound gameRound = new GameRound(totalRound);
        for (int count = 0; count < executeTimes; count++) {
            gameRound.increaseRound();
        }
        assertThat(gameRound.isPlayable()).isEqualTo(expectedIsEnd);
    }

}
