package racing.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    // 테스트를 위한 메서드
    public Car getCar(int n) {
        return cars.get(n);
    }

    public List<String> getPrintForm() {
        List<String> forms = new ArrayList<>();
        for (Car car : cars) {
            forms.add(car.getCarPositionForm());
        }
        return forms;
    }

    public List<String> getWinners() {
        int winnerPosition = getWinnerPosition();
        return findWinners(winnerPosition);
    }

    private int getWinnerPosition() {
        int winnerPosition = 0;
        for (Car car : cars) {
            winnerPosition = car.getCarPosition(winnerPosition);
        }
        return winnerPosition;
    }

    private List<String> findWinners(int winnerPosition) {
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == winnerPosition) {
                winners.add(car.getName());
            }
        }
        return winners;
    }

    public List<Car> getCars() {
        return cars;
    }
}
