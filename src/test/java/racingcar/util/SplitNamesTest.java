package racingcar.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.utils.SplitCarNames;

public class SplitNamesTest {

    @Test
    @DisplayName("splitCarNames() : 매개변수로 주어진 carNames를 배열로 분리하는 기능")
    void test_splitCarNames() {
        // given
        String carNames = "pobi,crong,jay";

        // when
        List<String> splitCarNames = SplitCarNames.splitCarNames(carNames);

        // then
        assertThat(splitCarNames.size()).isEqualTo(3);
        assertThat(splitCarNames).isExactlyInstanceOf(List.of("pobi", "crong", "jay").getClass());
    }
}
