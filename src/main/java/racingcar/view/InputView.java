package racingcar.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String READ_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String READ_TRY_COUNT_MESSAGE = "시도할 횟수는 몇회인가요?";

    public List<String> readCarName() {
        System.out.println(READ_CAR_NAME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        return Arrays.stream(scanner.nextLine()
                .split(","))
                .map(String::strip)
                .collect(Collectors.toUnmodifiableList());
    }

    public String readTryCount() {
        System.out.println(READ_TRY_COUNT_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
