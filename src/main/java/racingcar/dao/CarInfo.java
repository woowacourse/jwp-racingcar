package racingcar.dao;

public class CarInfo {
    private final int id;
    private final int racingId;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public CarInfo(int id, int racingId, String name, int position, boolean isWinner) {
        this.id = id;
        this.racingId = racingId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }
}
