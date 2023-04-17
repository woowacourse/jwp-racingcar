package racingcar.view;

import java.util.Scanner;
import racingcar.dto.RaceRequest;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String INPUT_TRIAL_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";

    public RaceRequest getRaceRequest() {
        System.out.println(INPUT_CAR_NAME_MESSAGE);
        final String names = scanner.next();
        System.out.println(INPUT_TRIAL_COUNT_MESSAGE);
        final int count = scanner.nextInt();
        return new RaceRequest(names, count);
    }
}
