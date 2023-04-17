package racingcar.view;

import racingcar.util.ValueEditor;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public List<String> readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String input = scanner.nextLine();
        validateNotBlank(input);
        return ValueEditor.splitByComma(input);
    }

    public String readMoveCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        String input = ValueEditor.removeSpace(scanner.nextLine());
        validateNotBlank(input);
        return input;
    }

    private void validateNotBlank(String input) {
        if(input.isBlank()){
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }

}
