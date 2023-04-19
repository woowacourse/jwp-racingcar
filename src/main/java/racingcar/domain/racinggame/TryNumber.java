package racingcar.domain.racinggame;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TryNumber {
    private static final int MIN_TRU_NUMBER = 0;
    private static final int MAX_TRY_NUMBER = 1_000_000;
    
    private final int number;
    
    public TryNumber(int number) {
        validateOutOfRange(number);
        this.number = number;
    }
    
    private void validateOutOfRange(int tryNumber) {
        if (tryNumber < MIN_TRU_NUMBER || tryNumber > MAX_TRY_NUMBER) {
            throw new IllegalArgumentException("시도 횟수 범위를 벗어났습니다. 다시 입력해주세요.");
        }
    }
    
    public TryNumber decrease() {
        return new TryNumber(this.number - 1);
    }
    
    public boolean isFinished() {
        return this.number == 0;
    }
}
