package racingcar.view.outputview;

import racingcar.controller.CarResponse;
import racingcar.model.car.Cars;

import java.util.HashMap;
import java.util.List;

public abstract class OutputView {
    private HashMap<Integer, String> errorMessageTable = new HashMap<>();

    public OutputView() {
        initialErrorMessage();
    }

    protected void initialErrorMessage() {
        initialInputErrorMessage();
        initialCarsErrorMessage();
        initialCarErrorMessage();
        initialTrackErrorMessage();
    }

    abstract void initialInputErrorMessage();

    abstract void initialCarsErrorMessage();

    abstract void initialCarErrorMessage();

    abstract void initialTrackErrorMessage();

    public abstract void printCurrentCarsPosition(Cars cars);

    public abstract void printWinnerCars(Cars cars);

    protected void insertErrorMessage(int errorNumber, String errorMessage) {
        errorMessageTable.put(errorNumber, errorMessage);
    }

    public void printErrorMessage(int errorNumber) {
        System.out.println(errorMessageTable.get(errorNumber));
    }

}
