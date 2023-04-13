package racingcar.dto;

public class CarDto {

    private final String name;
    private final int position;
    private final boolean isWin;

    public CarDto(String name, int position, boolean isWin) {
        this.name = name;
        this.position = position;
        this.isWin = isWin;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWin() {
        return isWin;
    }
}
