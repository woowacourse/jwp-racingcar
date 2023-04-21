package racingcar.view;

import racingcar.view.message.ErrorMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import racingcar.view.message.Message;

public class InputView {

    public static final String DELIMITER = ",";
    public static final String FORMAT_OF_NUMBER = "\\d+";
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readCarNames() {
        System.out.println(Message.CAR_NAME_INPUT_GUIDE.getMessage());

        String carNamesContent = scanner.nextLine();
        validateCarNames(carNamesContent);

        return Arrays.asList(carNamesContent.split(DELIMITER));
    }

    private void validateCarNames(final String content) {
        if (content.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK_INPUT_ERROR_FOR_CAR_NAMES.getMessage());
        }
    }

    public int readGameRound() {
        System.out.println(Message.GAME_ROUND_INPUT_GUIDE.getMessage());

        String gameRoundContent = scanner.nextLine();
        validateGameRound(gameRoundContent);

        return Integer.parseInt(gameRoundContent);
    }

    private void validateGameRound(final String content) {
        if (!content.matches(FORMAT_OF_NUMBER)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER_ERROR_FOR_GAME_ROUND.getMessage());
        }
    }
}
