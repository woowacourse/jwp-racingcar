package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dao.CarRecord;

public class ResultDto {

    private String winners;
    private List<CarDto> racingCars;

    public ResultDto(List<CarRecord> carRecords) {
        this.winners = convertToString(carRecords);
        this.racingCars = convertToDto(carRecords);
    }

    private String convertToString(List<CarRecord> carRecords) {
        return carRecords.stream()
                .filter(CarRecord::isWinner)
                .map(CarRecord::getName)
                .collect(Collectors.joining(","));
    }

    private List<CarDto> convertToDto(List<CarRecord> carRecords) {
        return carRecords.stream()
                .map(carRecord -> CarDto.from(carRecord.toEntity()))
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

}
