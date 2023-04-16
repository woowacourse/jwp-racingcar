package racingcar.util;

import org.springframework.stereotype.Component;

@Component
public class RepeatCountValidator implements Validator {

    private static final String NUMBER_OF_TIME_ERROR = "시도할 횟수는 1~100 사이값만 가능합니다.";
    private static final int NUMBER_OF_TIME_LOWER_BOUND = 0;
    private static final int NUMBER_OF_TIME_UPPER_BOUND = 101;

    @Override
    public void validate(Object number) {
        if ((int) number <= NUMBER_OF_TIME_LOWER_BOUND || NUMBER_OF_TIME_UPPER_BOUND <= (int) number) {
            throw new IllegalArgumentException(NUMBER_OF_TIME_ERROR);
        }
    }
}
