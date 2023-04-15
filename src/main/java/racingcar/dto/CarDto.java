package racingcar.dto;

import racingcar.domain.Car;

public class CarDto {
    private String name;
    private int identifier;
    private int position;
    
    public CarDto() { }
    
    public CarDto(Car car) {
        this.name = car.getName();
        this.position = car.getPosition();
        this.identifier = car.getIdentifier();
    }
    
    public String getName() {
        return name;
    }
    
    public int getIdentifier() {
        return identifier;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
}
