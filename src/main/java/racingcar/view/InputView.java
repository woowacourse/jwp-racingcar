package racingcar.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final int MIN_TRIAL_COUNT = 1;
    private final String CAR_NAME_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> inputCarNames() {
        String carNames = scanner.nextLine();
        return List.of(carNames.split(CAR_NAME_DELIMITER));
    }

    public int inputTrialCount() {
        return validateTrialCount(scanner.nextLine());
    }

    protected int validateTrialCount(String input) {
        int trialCount = validateTrialCountType(input);
        validateTrialCountRange(trialCount);

        return trialCount;
    }

    private int validateTrialCountType(String trialCount) {
        try {
            return Integer.parseInt(trialCount);
        } catch (Exception e) {
            throw new IllegalArgumentException("시도 횟수는 숫자만 입력해야합니다.");
        }
    }

    private void validateTrialCountRange(int trialCount) {
        if (trialCount < MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 최소 1회 이상입니다.");
        }
    }
}
