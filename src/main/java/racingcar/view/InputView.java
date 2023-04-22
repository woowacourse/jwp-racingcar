package racingcar.view;

import racingcar.controller.console.ConsoleCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

public class InputView {
    private static final Pattern REGEX = Pattern.compile("^[0-9]+$");
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        try {
            return List.of(br.readLine().split(","));
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

    public ConsoleCommand readCommand() {
        System.out.println("콘솔용 자동차 게임 경주 입력 명령어" + System.lineSeparator() +
                "race -> 게임을 진행한다." + System.lineSeparator() +
                "history -> 게임 결과 히스토리를 출력한다.");
        try {
            return ConsoleCommand.findBy(br.readLine());
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return readCommand();
        }
    }
}
