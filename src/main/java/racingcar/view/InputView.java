package racingcar.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final int ROUND_MIN_NUM = 1;
    private static final Pattern pattern = Pattern.compile("^[0-9]+$");

    private static final Scanner scanner = new Scanner(System.in);

    public String readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return scanner.nextLine();
    }

    public int readRacingRound() {
        System.out.println("시도할 회수는 몇회인가요?");
        String round = scanner.nextLine().trim();
        validateRoundInput(round);
        return Integer.parseInt(round);
    }

    private void validateRoundInput(String round) {
        Matcher matcher = pattern.matcher(round);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 라운드는 숫자여야 합니다.");
        }
        if (Integer.parseInt(round) < ROUND_MIN_NUM) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 1이상이어야 합니다.");
        }
    }
}
