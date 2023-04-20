package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;
import racingcar.domain.car.Winners;

public class ResultDto {

    private String winners;
    private List<CarDto> racingCars;

    private ResultDto(String winnerNames, List<CarDto> racingCars) {
        this.winners = winnerNames;
        this.racingCars = racingCars;
    }

    public static ResultDto of(List<Car> racingCars, Winners winners) {
        return new ResultDto(convertCarToWinnersString(winners), convertCarToCarDto(racingCars));
    }

    public static ResultDto fromRecords(List<CarRecordDto> racingCars) {
        return new ResultDto(convertRecordToWinnersString(racingCars), convertRecordToCarDto(racingCars));
    }

    private static String convertRecordToWinnersString(List<CarRecordDto> racingCars) {
        return racingCars.stream()
                .filter(CarRecordDto::isWinner)
                .map(CarRecordDto::getName)
                .collect(Collectors.joining(","));
    }

    private static List<CarDto> convertRecordToCarDto(List<CarRecordDto> racingCars) {
        return racingCars.stream()
                .map(CarDto::fromRecord)
                .collect(Collectors.toList());
    }

    private static String convertCarToWinnersString(Winners winners) {
        return winners.getCars()
                .stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private static List<CarDto> convertCarToCarDto(List<Car> racingCars) {
        return racingCars.stream()
                .map(CarDto::fromCar)
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

}
