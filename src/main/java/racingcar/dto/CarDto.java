package racingcar.dto;

import racingcar.domain.car.Car;

public class CarDto {

    private String name;
    private int position;

    private CarDto(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static CarDto fromCar(Car car) {
        return new CarDto(car.getName(), car.getPosition());
    }

    public static CarDto fromRecord(CarRecordDto carRecordDto) {
        return new CarDto(carRecordDto.getName(), carRecordDto.getPosition());
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

}
