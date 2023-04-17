package racing.domain;

import static racing.ui.output.OutputView.HYPHEN;

import java.util.List;
import racing.ui.output.OutputView;

public class Car {

    private final CarName carName;
    private int step;

    public Car(CarName carName) {
        this.carName = carName;
        this.step = 0;
    }

    public Car(CarName carName, int step) {
        this.carName = carName;
        this.step = step;
    }

    public void move() {
        this.step++;
    }


    public List<String> ifMeetAddWinners(List<String> winners, int winnerStep) {
        if (step == winnerStep) {
            winners.add(carName.getValue());
            return winners;
        }
        return winners;
    }

    public String getCarStepForm() {
        return carName.getValue() + OutputView.COLON + HYPHEN.repeat(step);
    }

    public int getCarStep(int winnerStep) {
        return Math.max(winnerStep, step);
    }

    public String getName() {
        return carName.getValue();
    }

    public int getStep() {
        return step;
    }

}
