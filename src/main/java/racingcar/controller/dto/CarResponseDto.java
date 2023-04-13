package racingcar.controller.dto;

public class CarResponseDto {

    private int id;
    private String name;
    private int position;

    public CarResponseDto(int id, String name, int position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
