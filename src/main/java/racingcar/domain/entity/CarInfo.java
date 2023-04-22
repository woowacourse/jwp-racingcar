package racingcar.domain.entity;

public class CarInfo {
    private final Integer id;
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

    public CarInfo(int racingId, String name, int position, boolean isWinner) {
        this.id = null;
        this.racingId = racingId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public Integer getId() {
        return id;
    }

    public int getRacingId() {
        return racingId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsWinner() {
        return isWinner;
    }
}
