package racingcar.view;

import racingcar.domain.TryCount;
import racingcar.dto.input.CarNameRequest;
import racingcar.dto.input.TryCountRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String DELIMITER = ",";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public CarNameRequest readCarNames() {
        printMessage("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        final List<String> input = Arrays.stream(readLine().split(DELIMITER))
                .collect(Collectors.toUnmodifiableList());
        validateDuplicatedCarNames(input);

        return new CarNameRequest(input);
    }

    public TryCountRequest readTryCount() {
        printMessage("시도할 횟수는 몇회인가요?");
        final int input = validateCount(readLine());

        return new TryCountRequest(new TryCount(input));
    }

    private void validateDuplicatedCarNames(final List<String> input) {
        if (input.size() != input.stream().distinct().count()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    private int validateCount(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.", exception);
        }
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void printMessage(final String message) {
        System.out.print(message + System.lineSeparator());
    }

}
