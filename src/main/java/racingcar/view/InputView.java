package racingcar.view;

import java.util.Scanner;

public class InputView {
    
    public static final String CAR_NAME_PROMPT = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    public static final String READ_COUNT_PROMPT = "시도할 회수는 몇회인가요?";
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final String CAR_NAMES_DELIMITER = ",";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String CAR_NAMES_BLANK_ERROR = ERROR_PREFIX + "경주할 자동차 이름이 입력되지 않았습니다.";
    private static final String CAR_NAMES_DUPLICATE_ERROR = ERROR_PREFIX + "경주할 자동차 이름이 중복되었습니다.";
    private static final String CAR_NAME_LENGTH_ERROR = ERROR_PREFIX + "경주할 자동차 이름이 5글자 초과했습니다.";
    private static final String CAR_NAME_BLANK_ERROR = ERROR_PREFIX + "각 자동차 이름은 빈 값일 수 없습니다.";
    private static final String TRY_NUM_BLANK_ERROR = ERROR_PREFIX + "시도할 횟수가 입력되지 않았습니다.";
    private static final String TRY_NUM_NOT_INTEGER_ERROR = ERROR_PREFIX + "시도할 횟수가 정수가 아닙니다.";
    private static final String TRY_NUM_NOT_POSITIVE_ERROR = ERROR_PREFIX + "시도할 횟수는 1 이상이어야 합니다.";
    private final Scanner scanner = new Scanner(System.in);
    
    public String readCarNames() {
        System.out.println(CAR_NAME_PROMPT);
        return this.scanner.nextLine();
    }
    
    public int readCount() {
        System.out.println(READ_COUNT_PROMPT);
        return this.scanner.nextInt();
    }
}
