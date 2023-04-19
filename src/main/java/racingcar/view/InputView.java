package racingcar.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class InputView {
    private final BufferedReader reader;
    
    public InputView(BufferedReader reader) {
        this.reader = reader;
    }
    
    public String inputCarNames() {
        try {
            String inputCarNames = reader.readLine();
            validateCarNames(inputCarNames);
            return inputCarNames;
        } catch (IOException e) {
            return inputCarNames();
        }
    }
    
    private void validateCarNames(String inputCarNames) {
        validateNullOrEmpty(inputCarNames);
    }
    
    private void validateNullOrEmpty(String inputCarNames) {
        if (Objects.isNull(inputCarNames) || inputCarNames.isBlank()) {
            throw new IllegalArgumentException("null 또는 empty가 올 수 없습니다. 다시 입력해주세요. 입력된 names : " + inputCarNames);
        }
    }
}
