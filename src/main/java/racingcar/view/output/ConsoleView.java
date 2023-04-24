package racingcar.view.output;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

public class ConsoleView {

    private static final String RACING_RESULT_MESSAGE = System.lineSeparator() + "실행 결과";
    private static final String RACING_WINNER_MESSAGE = "%s가 최종 우승했습니다." + System.lineSeparator();
    private static final String DISTANCE_MARK = "-";
    private static final String CAR_INFO_DELIMITER = " : ";
    private static final String RESULT_DELIMITER = ", ";

    public void printAllRacingGames(List<RacingGame> racingGames) {
        for (RacingGame racingGame : racingGames) {
            System.out.println("우승자: " + racingGame.getWinners().stream()
                    .map(Car::getCarName)
                    .collect(Collectors.joining(",")));
            System.out.println();
            for (Car car : racingGame.getCars()) {
                System.out.println("이름: " + car.getCarName());
                System.out.println("이동 거리: " + car.getPosition());
            }
        }
    }

    public void printRacingStatus(List<Car> cars) {
        for (Car car : cars) {
            StringJoiner stringJoiner = new StringJoiner(CAR_INFO_DELIMITER);
            stringJoiner.add(makeCarNames(car));
            stringJoiner.add(makeCarTrack(car));

            System.out.println(stringJoiner);
        }
        System.out.println();
    }

    private String makeCarNames(Car car) {
        return car.getCarName();
    }

    private String makeCarTrack(Car car) {
        return DISTANCE_MARK.repeat(car.getPosition());
    }

    public void printRacingWinners(List<Car> cars) {
        StringJoiner stringJoiner = new StringJoiner(RESULT_DELIMITER);
        cars.forEach(car -> stringJoiner.add(car.getCarName()));

        System.out.printf(RACING_WINNER_MESSAGE, stringJoiner);
    }

    public void printGameResultMessage() {
        System.out.println(RACING_RESULT_MESSAGE);
    }

    public void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }
}
