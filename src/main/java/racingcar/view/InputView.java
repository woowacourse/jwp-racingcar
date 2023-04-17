package racingcar.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class InputView {
    private static final String CAR_NAME_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String MOVING_TRIAL_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final Pattern REGEX = Pattern.compile("^[0-9]+$");
    private static final String MOVING_TRIAL_NOT_INTEGER_ERROR = "[ERROR] 시도할 횟수는 숫자만 가능합니다.";

    public String readCarNames() {
        System.out.println(CAR_NAME_INPUT_MESSAGE);
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return br.readLine();
        } catch (IOException e) {
            return readCarNames();
        }
    }

    public int readMovingTrial() {
        System.out.println(MOVING_TRIAL_INPUT_MESSAGE);
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            validateInteger(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException | IOException e) {
            return readMovingTrial();
        }
    }

    private void validateInteger(String movingTrial) {
        if (!REGEX.matcher(movingTrial).matches()) {
            throw new IllegalArgumentException(MOVING_TRIAL_NOT_INTEGER_ERROR);
        }
    }
}
