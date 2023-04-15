package racingcar.dao.car.dto;

public class CarRegisterRequest {

    private final String name;
    private final int position;
    private final int playResultId;

    public CarRegisterRequest(final String name, final int position, final int playResultId) {
        this.name = name;
        this.position = position;
        this.playResultId = playResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getPlayResultId() {
        return playResultId;
    }
}
