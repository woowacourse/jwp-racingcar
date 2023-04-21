package racingcar.view;

import static racingcar.option.Option.MAX_TRIAL_COUNT_LENGTH;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern NOT_INTEGER_PATTERN = Pattern.compile("\\D+");

    public String inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String input = scanner.nextLine();
        validateNames(input.split(","));
        return input;
    }

    private void validateNames(String[] input) {
        if (List.of(input).size() != Set.of(input).size()) {
            throw new IllegalArgumentException("자동자 이름은 중복될 수 없습니다.");
        }
    }

    public int inputTrialCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        String input = scanner.nextLine();
        validateInteger(input);
        validateTrialCountUnderMaxInteger(input);
        return Integer.parseInt(input);
    }

    private void validateInteger(String input) {
        if (NOT_INTEGER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("시도 횟수는 정수여야 합니다");
        }
    }

    private void validateTrialCountUnderMaxInteger(String input) {
        if (input.length() >= MAX_TRIAL_COUNT_LENGTH) {
            throw new IllegalArgumentException("시도 횟수는 999999999이하여야 합니다.");
        }
    }
}
