package racingcar.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class InputView {
    private static final Pattern REGEX = Pattern.compile("^[0-9]+$");
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        try {
            return br.readLine();
        } catch (IOException e) {
            return readCarNames();
        }
    }

    public int readMovingTrial() {
        System.out.println("시도할 회수는 몇회인가요?");
        try {
            final String input = br.readLine();
            validateInteger(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException | IOException e) {
            return readMovingTrial();
        }
    }

    private void validateInteger(String movingTrial) {
        if (!REGEX.matcher(movingTrial).matches()) {
            throw new IllegalArgumentException("[ERROR] 시도할 횟수는 숫자만 가능합니다.");
        }
    }
}
