package racing.console.ui.input;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_CAR_NAME = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String INPUT_COUNT = "시도할 회수는 몇회인가요?";

    private static final Scanner sc = new Scanner(System.in);

    public List<String> readCarNames() {
        System.out.println(INPUT_CAR_NAME);
        String carNames = sc.nextLine();

        return List.of(carNames.split(","));
    }

    public String readTrialCount() {
        System.out.println(INPUT_COUNT);
        return sc.nextLine();
    }
}
