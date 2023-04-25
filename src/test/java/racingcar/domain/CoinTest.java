package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CoinTest {

    @DisplayName("isLeft()")
    @Nested
    class isLeft {

        @DisplayName("사용 가능한 코인은 true를 반환한다.")
        @Test
        void shouldBeTrueWhenUseAvailableCoin() {
            assertThat(new Coin(1).isLeft()).isTrue();
        }

        @DisplayName("사용 불가능한 코인은 false를 반환한다.")
        @Test
        void shouldBeFalseWhenUseNotAvailableCoin() {
            assertThat(new Coin(0).isLeft()).isFalse();
        }
    }

    @DisplayName("use()")
    @Nested
    class use {

        @DisplayName("1개 남은 코인을 사용하면 사용 불가능한 코인이 된다.")
        @Test
        void shouldBecomeNotAvailableCoinWhenUseCoinHaveRemainingOnlyOne() {
            Coin coin = new Coin(1);
            coin.use();
            assertThat(coin.isLeft()).isFalse();
        }

        @DisplayName("0개 남은 코인을 사용하면 예외가 발생한다.")
        @Test
        void shouldThrowExceptionWhenUseNotAvailableCoin() {
            Coin coin = new Coin(1);
            coin.use();
            assertThatThrownBy(coin::use)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("사용 불가능한 코인입니다.");
        }
    }
}
