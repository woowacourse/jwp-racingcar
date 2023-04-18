package racingcar.view.outputview;

import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.HasBlankCarNameException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.exception.InvalidTrialTimesFormatException;
import racingcar.exception.MaxAttemptInputException;
import racingcar.exception.NotExistCarsException;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;

import java.util.StringJoiner;
import java.util.stream.Collectors;

public class KoreanOutputView extends OutputView {
    public static final String POSITION_CAR_FORMAT_SYMBOL = "-";
    public static final String POSITION_CAR_STATE_FORMAT = "%s : %s";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";
    private static final String CAR_SEPARATOR = ", ";
    private static final String LINE_BREAK = "\n";

    @Override
    void initialInputErrorMessage() {
        super.insertErrorMessage(
                new MaxAttemptInputException().getErrorNumber(),
                "[ERROR] 최대 입력 횟수를 초과했습니다. 프로그램을 종료합니다.");
    }

    @Override
    void initialCarsErrorMessage() {
        super.insertErrorMessage(
                new NotExistCarsException().getErrorNumber(),
                "[ERROR] 게임에 참여한 자동차가 없습니다." + LINE_BREAK);
        super.insertErrorMessage(
                new DuplicateCarNamesException().getErrorNumber(),
                "[ERROR] 중복된 차 이름이 존재합니다." + LINE_BREAK);
    }

    @Override
    void initialCarErrorMessage() {
        super.insertErrorMessage(
                new ExceedCarNameLengthException().getErrorNumber(),
                "[ERROR] 자동차 이름은 다섯 글자 이하여야 합니다." + LINE_BREAK);
        super.insertErrorMessage(
                new HasBlankCarNameException().getErrorNumber(),
                "[ERROR] 비어있는 자동차 이름이 존재합니다." + LINE_BREAK);
        super.insertErrorMessage(
                new InvalidCarNameFormatException().getErrorNumber(),
                "[ERROR] 자동차 이름은 문자와 숫자만 가능합니다." + LINE_BREAK);
    }

    @Override
    void initialTrackErrorMessage() {
        super.insertErrorMessage(
                new InvalidRangeTrialTimesException().getErrorNumber(),
                "[ERROR] 시도 횟수는 1 이상 100 이하여야 합니다." + LINE_BREAK);
        super.insertErrorMessage(
                new InvalidTrialTimesFormatException().getErrorNumber(),
                "[ERROR] 시도 횟수는 숫자만 입력 가능합니다." + LINE_BREAK);
    }

    @Override
    public void printCurrentCarsPosition(final Cars cars) {
        StringJoiner stringJoiner = new StringJoiner(LINE_BREAK);

        cars.getCarsCurrentInfo().forEach(car -> {
            stringJoiner.add(String.format(POSITION_CAR_STATE_FORMAT,
                    car.getCarName(),
                    POSITION_CAR_FORMAT_SYMBOL.repeat(car.getPosition())));
        });

        System.out.println(stringJoiner + LINE_BREAK);
    }

    @Override
    public void printWinnerCars(final Cars cars) {
        String winnerCarsFormat = cars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(CAR_SEPARATOR));

        System.out.println(String.format(WINNER_MESSAGE, winnerCarsFormat));
    }
}
