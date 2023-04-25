package racingcar.domain.racinggame;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class Count {
    private static final int MIN_TRU_NUMBER = 0;
    private static final int MAX_TRY_NUMBER = 1_000_000;
    
    private final int count;
    private final int originalCount;
    
    public Count(final int count) {
        this(count, count);
        validateOutOfRange(count);
    }
    
    private Count(final int count, final int originalCount) {
        this.count = count;
        this.originalCount = originalCount;
    }
    
    private void validateOutOfRange(final int tryNumber) {
        if (tryNumber < MIN_TRU_NUMBER || tryNumber > MAX_TRY_NUMBER) {
            throw new IllegalArgumentException("시도 횟수 범위를 벗어났습니다. 다시 입력해주세요. 입력된 tryNumber : " + tryNumber);
        }
    }
    
    public Count decrease() {
        return new Count(this.count - 1, originalCount);
    }
    
    public boolean isFinished() {
        return this.count == 0;
    }
}
