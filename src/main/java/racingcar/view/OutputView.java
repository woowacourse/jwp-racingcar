package racingcar.view;


import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class OutputView {

    private static final String WIN = "가 최종 우승했습니다.";

    public void printRoundResult(List<Car> cars) {
        StringBuilder content = new StringBuilder();
        cars.forEach(car -> addCarResult(content, car));
        System.out.println(content);
    }

    private void addCarResult(StringBuilder roundResult, Car car) {
        final String delimiter = " : ";
        final String carResult =
            addDuplicateIdentifier(car) + delimiter + convertDistance(car.getPosition()) + '\n';
        roundResult.append(carResult);
    }

    private String addDuplicateIdentifier(Car car) {
        final String DELIMITER = "-";
        final int UNIQUE = 0;
        String name = car.getName().trim();
        int duplicateIdentifier = car.getIdentifier();
        if (duplicateIdentifier != UNIQUE) {
            return name + DELIMITER + duplicateIdentifier;
        }
        return name;
    }

    private String convertDistance(int distance) {
        final String DISTANCE = "-";
        return DISTANCE.repeat(distance);
    }

    public void printWinners(List<Car> winners) {
        final String DELIMITER = ", ";
        String message = winners.stream().map(this::addDuplicateIdentifier)
            .collect(Collectors.joining(DELIMITER)) + WIN;
        System.out.println(message);
    }
}
