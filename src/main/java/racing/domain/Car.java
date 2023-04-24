package racing.domain;

import racing.ui.output.OutputView;

import java.util.List;

import static racing.ui.output.OutputView.COLON;
import static racing.ui.output.OutputView.HYPHEN;

public class Car {

    private final String name;
    private int position;

    public Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        this.position++;
    }

    public List<String> ifMeetAddWinners(List<String> winners, int winnerPosition) {
        if (position == winnerPosition) {
            winners.add(name);
            return winners;
        }
        return winners;
    }

    public String getCarPositionForm() {
        return name + COLON + HYPHEN.repeat(position);
    }

    public int getCarPosition(int winnerPosition) {
        return Math.max(winnerPosition, position);
    }

}
