package racingcar.view;

import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.util.NameFormatConverter;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    private final static String PRINT_CAR_LOCATION = "%s : %d" + System.lineSeparator();
    private final static String PRINT_WINNER = "%s가 최종 우승했습니다." + System.lineSeparator();
    private final static String PRINT_REQUEST_CAR_NAME
            = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private final static String PRINT_REQUEST_TRY_COUNT = "시도할 회수는 몇회인가요?";

    public void printResult(Cars cars) {
        for (Car car : cars.getCars()) {
            System.out.printf(PRINT_CAR_LOCATION, car.getName(), car.getLocation());
        }
        System.out.println();
    }

    public void printWinner(List<Car> winner) {
        List<String> winners = new ArrayList<>();
        for(Car car : winner){
            winners.add(car.getName());
        }
        System.out.printf(PRINT_WINNER, NameFormatConverter.joinNameWithDelimiter(winners));
    }

    public void printRequestCarName() {
        System.out.println(PRINT_REQUEST_CAR_NAME);
    }

    public void printRequestTryCount() {
        System.out.println(PRINT_REQUEST_TRY_COUNT);
    }
}
