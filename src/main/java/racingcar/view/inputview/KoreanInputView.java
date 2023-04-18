package racingcar.view.inputview;

import java.util.Scanner;

public class KoreanInputView extends InputView {

    public KoreanInputView(Scanner scanner) {
        super(scanner);
    }

    @Override
    public String inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return super.nextLine();
    }

    @Override
    public String inputTrialTimes() {
        System.out.println("시도할 횟수는 몇회인가요?");
        return super.nextLine();
    }
}
