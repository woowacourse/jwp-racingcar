package racingcar.view;

import racingcar.utils.InputReader;

public class InputView {

    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "입력값은 비어있을 수 없습니다.";
    private final InputReader inputReader;

    public InputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public String getCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,)를 기준으로 구분).");
        return inputReader.readInput();
    }

    public String getTrial() {
        System.out.println("시도할 횟수는 몇회인가요?");
        return inputReader.readInput();
    }
}
