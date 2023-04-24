package racingcar.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters"})
class CountTest {

    @Test
    void 종료_여부를_반환하다() {
        final Count count = new Count(3);
        assertFalse(count.isFinished());
        count.next();
        assertFalse(count.isFinished());
        count.next();
        assertFalse(count.isFinished());
        count.next();
        assertTrue(count.isFinished());
    }

    @Test
    void 음수가_들어오면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new Count(-1));
    }
}
