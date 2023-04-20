package racingcar.domain.racinggame;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class TryNumber {
    private static final int MIN_TRU_NUMBER = 0;
    private static final int MAX_TRY_NUMBER = 1_000_000;
    
    private final int number;
    private final int originalNumber;
    
    public TryNumber(final int number) {
        this(number, number);
        validateOutOfRange(number);
    }
    
    private TryNumber(final int number, final int originalNumber) {
        this.number = number;
        this.originalNumber = originalNumber;
    }
    
    private void validateOutOfRange(final int tryNumber) {
        if (tryNumber < MIN_TRU_NUMBER || tryNumber > MAX_TRY_NUMBER) {
            throw new IllegalArgumentException("시도 횟수 범위를 벗어났습니다. 다시 입력해주세요. 입력된 tryNumber : " + tryNumber);
        }
    }
    
    public TryNumber decrease() {
        return new TryNumber(this.number - 1, originalNumber);
    }
    
    public boolean isFinished() {
        return this.number == 0;
    }
}
