package racingcar.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String GAME_ROUND_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public String inputCarName() {
        System.out.println(CAR_NAMES_INPUT_MESSAGE);

        return readLine();
    }

    public int inputGameRound() {
        System.out.println(GAME_ROUND_INPUT_MESSAGE);
        String inputGameRound = readLine();
        return stringToIntConverter(inputGameRound);
    }

    private String readLine() {
        String input;
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("다시 입력해주세요.");
        }
        return input;
    }

    public int stringToIntConverter(String input) {
        return Integer.parseInt(input);
    }
}
