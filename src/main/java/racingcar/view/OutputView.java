package racingcar.view;

import static java.util.stream.Collectors.joining;

import java.util.List;
import racingcar.dto.CarDto;
import racingcar.dto.GamePlayResponseDto;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";

    public void printResult(final GamePlayResponseDto response) {
        System.out.println("실행 결과");
        printCurrentCarPositions(response.getRacingCars());
        printWinnersMessage(response.getWinners());
    }

    private void printCurrentCarPositions(final List<CarDto> cars) {
        System.out.println(generatePositionMessages(cars) + NEXT_LINE);
    }

    private String generatePositionMessages(final List<CarDto> cars) {
        return cars.stream()
                .map(this::generatePositionMessage)
                .collect(joining(NEXT_LINE));
    }

    private String generatePositionMessage(final CarDto car) {
        return String.format("Name: %s : Position: %d", car.getName(), car.getPosition());
    }

    private void printWinnersMessage(final List<String> winners) {
        String winnersMessage = String.join(DELIMITER, winners);
        System.out.println(String.format("%s가 최종 우승했습니다.", winnersMessage));
    }

    public void printErrorMessage(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
