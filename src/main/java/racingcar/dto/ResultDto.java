package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dao.CarRecord;
import racingcar.domain.car.Car;

public class ResultDto {

    private String winners;
    private List<CarDto> racingCars;

    private ResultDto(String winnerNames, List<CarDto> racingCars) {
        this.winners = winnerNames;
        this.racingCars = racingCars;
    }

    public static ResultDto ofCars(List<Car> racingCars, List<Car> winners) {
        return new ResultDto(convertCarToWinnersString(winners), convertCarToCarDto(racingCars));
    }

    public static ResultDto fromRecords(List<CarRecord> racingCars) {
        return new ResultDto(convertRecordToWinnersString(racingCars), convertRecordToCarDto(racingCars));
    }

    private static String convertRecordToWinnersString(List<CarRecord> racingCars) {
        return racingCars.stream()
                .filter(CarRecord::isWinner)
                .map(CarRecord::getName)
                .collect(Collectors.joining(","));
    }

    private static List<CarDto> convertRecordToCarDto(List<CarRecord> racingCars) {
        return racingCars.stream()
                .map(CarDto::fromRecord)
                .collect(Collectors.toList());
    }

    private static String convertCarToWinnersString(List<Car> winners) {
        return winners.stream()
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
