package racingcar.view;

import java.util.List;
import racingcar.utils.ScannerUtil;
import racingcar.utils.SplitCarNames;

public class InputView {

    public List<String> inputCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        final String carNames = ScannerUtil.readLine();
        return SplitCarNames.splitCarNames(carNames);
    }

    public int inputTryCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        return ScannerUtil.readNumber();
    }
}
