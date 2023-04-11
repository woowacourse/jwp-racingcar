package racingcar.console;

import racingcar.utils.ScannerUtil;
import racingcar.utils.SplitCarNames;

public class InputView {

    public String[] inputCarNames() {
        final String carNames = ScannerUtil.readLine();
        return SplitCarNames.splitCarNames(carNames);
    }

    public int inputTryCount() {
        return ScannerUtil.readNumber();
    }
}
