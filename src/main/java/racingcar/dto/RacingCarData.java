package racingcar.dto;

public class RacingCarData {

    private final String name;
    private final int position;


    public RacingCarData(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
