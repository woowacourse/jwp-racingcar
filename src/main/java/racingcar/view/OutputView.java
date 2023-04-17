package racingcar.view;

import java.util.List;
import racingcar.RaceDto;
import racingcar.dto.CarPositionDto;

public class OutputView {

    private static final String STATUS_GUIDE_MESSAGE = "실행 결과";
    private static final String COLON = " : ";

    private OutputView() {
    }

    private static void printOneStatus(CarPositionDto racingStatus) {
        System.out.print(racingStatus.getCarName());
        System.out.print(COLON);
        System.out.print(racingStatus.getStatus());
        System.out.println();
    }

    public static void printResult(RaceDto raceDto) {
        System.out.println();
        System.out.println(STATUS_GUIDE_MESSAGE);

        final List<CarPositionDto> carPositionDtos = raceDto.getCarPositionDtos();

        for (CarPositionDto carPositionDto : carPositionDtos) {
            printOneStatus(carPositionDto);
        }
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
