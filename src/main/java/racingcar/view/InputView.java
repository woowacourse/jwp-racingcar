package racingcar.view;

import java.util.Scanner;
import racingcar.exception.CarNameLengthException;
import racingcar.exception.TryCountException;

public class InputView {
    private static final int MIN_COUNT_SIZE = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DIVISION_CHAR = ",";
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final int MIN_CAR_NAME_LENGTH = 1;


    public String inputCarName() {
        String inputCars = scanner.nextLine();
        validateInputCarNames(inputCars.split(DIVISION_CHAR));
        return inputCars;
    }

    public int inputCount() {
        int inputCount = scanner.nextInt();
        validateInputCount(inputCount);
        return inputCount;
    }

    private void validateInputCarNames(String[] inputCarNames) {
        for (String carName : inputCarNames) {
            validateInputCarName(carName);
        }
    }

    private void validateInputCarName(String carName) {
        if (carName.trim().length() > MAX_CAR_NAME_LENGTH || carName.trim().length() < MIN_CAR_NAME_LENGTH) {
            throw new CarNameLengthException();
        }
    }

    private void validateInputCount(int count) {
        if (count < MIN_COUNT_SIZE) {
            throw new TryCountException();
        }
    }
}
