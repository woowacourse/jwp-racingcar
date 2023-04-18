package racingcar.view.outputview;

import racingcar.exception.CustomException;
import racingcar.model.car.Cars;

import java.util.HashMap;

public abstract class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    private HashMap<Integer, String> errorMessageTable = new HashMap<>();

    public OutputView() {
        initialErrorMessage();
    }

    protected void initialErrorMessage() {
        initialCarsErrorMessage();
        initialCarErrorMessage();
        initialTrackErrorMessage();
    }

    abstract void initialCarsErrorMessage();

    abstract void initialCarErrorMessage();

    abstract void initialTrackErrorMessage();

    public abstract void printCurrentCarsPosition(Cars cars);

    public abstract void printWinnerCars(Cars cars);

    protected void insertErrorMessage(final CustomException customException) {
        final int errorNumber = customException.getErrorNumber();
        final String errorMessage = customException.getErrorMessage();

        errorMessageTable.put(errorNumber, errorMessage);
    }

    public void printErrorMessage(final int errorNumber) {
        System.out.println(ERROR_PREFIX + errorMessageTable.get(errorNumber));
    }

}
