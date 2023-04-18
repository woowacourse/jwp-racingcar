package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class NamesTest {
    @DisplayName("중복된 이름이 존재하면, 예외가 발생한다.")
    @Test
    void createDuplicateNamesTest() {
        Assertions.assertThatThrownBy(() -> Names.from(List.of("찰리", "찰리")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
