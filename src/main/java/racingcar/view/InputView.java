package racingcar.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
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
            final String inputCarNames = reader.readLine();
            validateCarNames(inputCarNames);
            return inputCarNames;
        } catch (IOException e) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요. Error Message : " + e.getMessage());
        }
    }
    
    private void validateCarNames(final String inputCarNames) {
        validateNullOrEmpty(inputCarNames);
        validateCarNamesFormat(inputCarNames);
    }
    
    private void validateNullOrEmpty(final String inputCarNames) {
        if (Objects.isNull(inputCarNames) || inputCarNames.isBlank()) {
            throw new IllegalArgumentException("null 또는 empty가 올 수 없습니다. 다시 입력해주세요. 입력된 names : " + inputCarNames);
        }
    }
    
    private void validateCarNamesFormat(final String inputCarNames) {
        final Matcher matcher = PATTERN.matcher(inputCarNames);
        if (matcher.find()) {
            throw new IllegalArgumentException("차 이름은 쉼표로 구분해서 영어와 한글로만 입력할 수 있습니다. 다시 입력해주세요. 입력된 names : " + inputCarNames);
        }
    }
    
    public int inputTryNumber() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요. Error Message : " + e.getMessage());
        }
    }
}
