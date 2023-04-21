package racingcar.view.outputview;

import java.util.HashMap;
import java.util.List;
import racingcar.controller.CarResponse;

public abstract class OutputView {
    private final HashMap<Integer, String> errorMessageTable = new HashMap<>();

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

    public abstract void printCurrentCarsPosition(List<CarResponse> carResponses);

    public abstract void printWinnerCars(String carNames);

    protected void insertErrorMessage(int errorNumber, String errorMessage) {
        errorMessageTable.put(errorNumber, errorMessage);
    }
}
