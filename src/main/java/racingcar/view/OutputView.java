package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Position;
import racingcar.dto.output.PrintCriticalExceptionDto;
import racingcar.dto.output.PrintExceptionDto;
import racingcar.dto.output.PrintMovingStatusDto;
import racingcar.dto.output.PrintWinnersDto;

import java.util.List;
import java.util.StringJoiner;

public final class OutputView {

    public void printTotalMovingStatus(PrintMovingStatusDto dto) {
        print("\n실행 결과");
        List<Cars> totalMovingStatus = dto.getMovingStatus();
        for (Cars movingStatus : totalMovingStatus) {
            printMovingStatus(movingStatus);
        }

        printMovingStatus(totalMovingStatus.get(totalMovingStatus.size() - 1));
    }

    private void printMovingStatus(Cars cars) {
        for (Car car : cars) {
            print(String.format(
                    "%s : %s",
                    car.getName(), drawMovingLength(car.getPosition()))
            );
        }
        System.out.println();
    }

    private String drawMovingLength(Position position) {
        return "-".repeat(Math.max(0, position.getPosition()));
    }


    public void printWinners(PrintWinnersDto dto) {
        Cars cars = dto.getCars();
        StringJoiner stringJoiner = new StringJoiner(", ", "", "");
        for (Car car : cars) {
            stringJoiner.add(car.getName().toString());
        }
        print(String.format("%s가 최종 우승했습니다.", stringJoiner));
    }

    public void printException(PrintExceptionDto dto) {
        print(ErrorMessage.ERROR_HEAD + dto.getException().getMessage());
    }

    public void printCriticalException(PrintCriticalExceptionDto dto) {
        print(ErrorMessage.UNEXPECTED_ERROR,
                ErrorMessage.ERROR_HEAD + dto.getException().getMessage());
    }

    private void print(String... messages) {
        for (String message : messages) {
            System.out.println(message);
            ;
        }
    }

    private static final class ErrorMessage {
        private static final String ERROR_HEAD = "[ERROR] ";
        private static final String UNEXPECTED_ERROR = "예기치 못한 오류가 발생했습니다.";
    }
}
