package racingcar.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String CAR_NAMES_NOT_VALID_REGEX = "[^a-zA-Z가-힣,]";
    private static final Pattern PATTERN = Pattern.compile(CAR_NAMES_NOT_VALID_REGEX);
    
    private final BufferedReader reader;
    
    public InputView() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public String inputCarNames() {
        try {
            System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
            final String inputCarNames = reader.readLine();
            validateCarNames(inputCarNames);
            return inputCarNames;
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해주세요. Error message : " + e.getMessage());
        }
    }
    
    private void validateCarNames(final String inputCarNames) {
        validateNullOrEmpty(inputCarNames);
        validateCarNamesFormat(inputCarNames);
    }
    
    private void validateNullOrEmpty(final String inputCarNames) {
        if (Objects.isNull(inputCarNames) || inputCarNames.isBlank()) {
            throw new IllegalArgumentException("[ERROR] null 또는 empty가 올 수 없습니다. 다시 입력해주세요. 입력된 data : " + inputCarNames);
        }
    }
    
    private void validateCarNamesFormat(final String inputCarNames) {
        final Matcher matcher = PATTERN.matcher(inputCarNames);
        if (matcher.find()) {
            throw new IllegalArgumentException("[ERROR] 차 이름은 쉼표로 구분해서 영어와 한글로만 입력할 수 있습니다. 다시 입력해주세요. 입력된 names : " + inputCarNames);
        }
    }
    
    public int inputTryNumber() {
        try {
            System.out.println("시도할 회수는 몇회인가요?");
            final String inputTryNumber = reader.readLine();
            validateNullOrEmpty(inputTryNumber);
            return Integer.parseInt(inputTryNumber);
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 숫자만 입력할 수 있습니다. 다시 입력해주세요. Error message : " + e.getMessage());
        }
    }
}
