package racingcar.view;

import static racingcar.view.ConsoleCommand.PLAY;
import static racingcar.view.ConsoleCommand.RECORDS;

import java.util.List;
import racingcar.view.util.KeyboardReader;
import racingcar.view.util.TextParser;

public class InputView {

    private static final String CAR_NAMES_DELIMITER = ",";

    private static final String ASK_COMMAND_MESSAGE = "원하는 기능을 입력하세요(플레이: " + PLAY.getCommand()
            + ", 이력 조회: " + RECORDS.getCommand() + ")";
    private static final String ASK_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String ASK_RACING_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";

    public List<String> askCarNames() {
        System.out.println(ASK_CAR_NAMES_MESSAGE);
        return TextParser.parseByDelimiter(KeyboardReader.readLine(), CAR_NAMES_DELIMITER);
    }

    public int askRacingCount() {
        System.out.println(ASK_RACING_COUNT_MESSAGE);
        return KeyboardReader.readNaturalNumber();
    }

    public ConsoleCommand askCommand() {
        System.out.println(ASK_COMMAND_MESSAGE);
        return ConsoleCommand.match(KeyboardReader.readLine());
    }

}
