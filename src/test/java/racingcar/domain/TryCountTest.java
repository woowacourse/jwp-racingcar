package racingcar.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TryCountTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void 생성_실패_0미만(int count) {
        assertThatThrownBy(() -> new TryCount(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1보다 큰 시도 횟수만 만들 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 10})
    void decrease_메서드는_카운트를_1만큼_감소시킨다(int initCount) {
        TryCount tryCount = new TryCount(initCount);
        tryCount.decrease();
        assertThat(tryCount.getCount()).isEqualTo(initCount - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 10})
    void decrease_실패_0이하(int initCount) {
        TryCount tryCount = new TryCount(initCount);
        decreaseCountUntilZero(initCount, tryCount);
        assertThatThrownBy(() -> tryCount.decrease())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수를 감소시킬 수 없습니다.");
    }

    private static void decreaseCountUntilZero(int initCount, TryCount tryCount) {
        for (int i = 0; i < initCount; i++) {
            tryCount.decrease();
        }
    }
}
