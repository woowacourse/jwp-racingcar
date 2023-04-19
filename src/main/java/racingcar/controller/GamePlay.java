package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.dto.CarDto;
import racingcar.dto.PlayResponseDto;
import racingcar.genertor.NumberGenerator;
import racingcar.genertor.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GamePlay {

    private final NumberGenerator numberGenerator;

    public GamePlay() {
        this.numberGenerator = new RandomNumberGenerator();
    }

    public GamePlay(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public void gameStart() {
        final Cars cars = new Cars(repeatUntilGetValidInput(this::getCarsByConsole));
        int tryTimes = repeatUntilGetValidInput(this::readTryTimeByConsole);
        play(cars, tryTimes);
        printResult(cars);
    }

    private int readTryTimeByConsole() {
        OutputView.printInputTryTimesNotice();
        final int tryTimes = InputView.inputTryTimes();
        validateTryTimes(tryTimes);
        return tryTimes;
    }

    private List<Car> getCarsByConsole() {
        OutputView.printInputCarNamesNotice();
        return InputView.inputCarNames().stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void play(Cars cars, int tryTimes) {
        while (tryTimes-- > 0) {
            cars.moveCars(numberGenerator);
        }
    }

    private void validateTryTimes(int tryTimes) {
        if (tryTimes < 1) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 최소 1회 이상입니다.");
        }
    }

    private void printResult(final Cars cars) {
        OutputView.printResultNotice();
        OutputView.printWinner(makePlayResultDto(cars));
    }

    private PlayResponseDto makePlayResultDto(Cars cars) {
        return new PlayResponseDto(makeWinnerString(cars), makeCarsDto(cars));
    }

    private List<CarDto> makeCarsDto(final Cars cars) {
        return cars.getCars().stream()
                .map(CarDto::createCarDto)
                .collect(Collectors.toList());
    }

    private static String makeWinnerString(final Cars cars) {
        return cars.findWinners().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public <T> T repeatUntilGetValidInput(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            return repeatUntilGetValidInput(supplier);
        }
    }
}
