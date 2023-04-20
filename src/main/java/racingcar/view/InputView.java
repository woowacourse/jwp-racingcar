package racingcar.view;

import java.util.Scanner;

public class InputView {

    private static final String NUMBER_OF_TIME_HEADER = "시도할 횟수는 몇회인가요?";
    public static final String CAR_NAMES_HEADER = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";

    private final Scanner console;

    public InputView() {
        console = new Scanner(System.in);
    }

    public String getCarNames() {
        System.out.println(CAR_NAMES_HEADER);
        return console.nextLine();
    }

    public int getTryCount() {
        System.out.println(NUMBER_OF_TIME_HEADER);
        String number = console.nextLine();

        return Integer.parseInt(number);
    }
}
