package racing.domain;

import static racing.ui.output.OutputView.HYPHEN;

import java.util.List;
import racing.ui.output.OutputView;

public class Car {

    private final String name;
    private int step;

    public Car(String name) {
        this.name = name;
        this.step = 0;
    }

    public Car(String name, int step) {
        this.name = name;
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public int getStep() {
        return step;
    }

    public void move() {
        this.step++;
    }


    public List<String> ifMeetAddWinners(List<String> winners, int winnerStep) {
        if (step == winnerStep) {
            winners.add(name);
            return winners;
        }
        return winners;
    }

    public String getCarStepForm() {
        return name + OutputView.COLON + HYPHEN.repeat(step);
    }

    public int getCarStep(int winnerStep) {
        return Math.max(winnerStep, step);
    }

}
