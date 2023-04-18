package racingcar.view;

import racingcar.common.exception.DuplicateResourceException;
import racingcar.common.exception.ResourceLengthException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {

    private static final String CAR_NAME_DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static <T> T getUserInput(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (DuplicateResourceException e) {
            OutputView.printMessage("중복된 값을 입력할 수 없습니다.");
            return getUserInput(inputReader);
        } catch (ResourceLengthException e) {
            OutputView.printMessage(String.format("최대 %d글자까지 입력할 수 있습니다.", e.getLength().getData()));
            return getUserInput(inputReader);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return getUserInput(inputReader);
        }
    }

    public static List<String> getCarNames() {
        return getUserInput(() -> {
            OutputView.printMessage("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
            return Arrays.asList(readConsole().split(CAR_NAME_DELIMITER));
        });
    }

    public static int getTryCount() {
        return getUserInput(() -> {
            OutputView.printMessage("시도할 회수는 몇회인가요?");
            return Integer.parseInt(readConsole());
        });
    }

    private static String readConsole() {
        final String command = scanner.nextLine();
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
        return command;
    }
}
