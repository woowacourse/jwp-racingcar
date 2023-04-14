package racingcar.dto;

import racingcar.model.Car;
import racingcar.util.NameFormatConverter;

import java.util.ArrayList;
import java.util.List;

public class ResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public ResponseDto(List<String> winners, List<Car> racingCars) {
        this.winners = NameFormatConverter.joinNameWithDelimiter(winners);
        this.racingCars = convert(racingCars);
    }

    private List<CarDto> convert(List<Car> cars){
        List<CarDto> carDtos = new ArrayList<>();
        for(Car car : cars){
            carDtos.add(new CarDto(car.getName(),car.getLocation()));
        }
        return carDtos;
    }
    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
