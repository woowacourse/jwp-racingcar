package racingcar.view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameHistoriesResponseDto;

public class OutputView {

    private static final String STATUS_SEPARATOR = " : ";
    private static final String NAME_DELIMITER = ", ";
    private static final String NEW_LINE = System.lineSeparator();

    public void printResultMessage() {
        System.out.println(NEW_LINE + "실행 결과");
    }

    public void printWinners(final GameHistoriesResponseDto gameHistory) {
        System.out.println(gameHistory.getWinners() + "가 최종 우승했습니다." + NEW_LINE);
    }

    public void printCurrentRacingStatus(final GameHistoriesResponseDto gameHistory) {
        final Map<String, Integer> currentRacingStatus = getCurrentRacingStatus(gameHistory.getRacingCars());
        StringBuilder currentRacingStatusMessage = new StringBuilder();

        currentRacingStatus.keySet().forEach(carName -> {
            currentRacingStatusMessage.append(carName)
                    .append(STATUS_SEPARATOR)
                    .append(currentRacingStatus.get(carName))
                    .append(NEW_LINE);
        });

        System.out.println(currentRacingStatusMessage);
    }

    private Map<String, Integer> getCurrentRacingStatus(final List<CarStatusResponseDto> carStatuses) {
        Map<String, Integer> carAndDistanceStatus = new LinkedHashMap<>();

        for (final CarStatusResponseDto carStatus : carStatuses) {
            carAndDistanceStatus.put(carStatus.getName(), carStatus.getPosition());
        }

        return carAndDistanceStatus;
    }
}
