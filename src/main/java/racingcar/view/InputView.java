package racingcar.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private static final String READ_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String READ_ATTEMPT_NUMBER_MESSAGE = "시도할 회수는 몇회인가요?";

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static String readCarNames() {
        System.out.println(READ_CAR_NAME_MESSAGE);
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return readCarNames();
        }
    }

    public static int readAttemptNumber() {
        System.out.println(READ_ATTEMPT_NUMBER_MESSAGE);
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            return readAttemptNumber();
        }
    }
}
