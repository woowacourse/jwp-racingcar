package racingcar.view;

import racingcar.view.util.KeyboardReader;

public class InputView {

    private static final String ASK_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String ASK_RACING_COUNT_MESSAGE = "시도할 회수는 몇회인가요?";

    public String askCarNames() {
        System.out.println(ASK_CAR_NAMES_MESSAGE);

        return KeyboardReader.readLine();
    }

    public int askRacingCount() {
        System.out.println(ASK_RACING_COUNT_MESSAGE);
        return KeyboardReader.readNaturalNumber();
    }
}
