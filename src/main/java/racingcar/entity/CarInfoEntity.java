package racingcar.entity;

public class CarInfoEntity {

    private int id;
    private int racingId;
    private String name;
    private int position;
    private boolean isWinner;

    public CarInfoEntity(int id, int racingId, String name, int position, boolean isWinner) {
        this.id = id;
        this.racingId = racingId;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public int getId() {
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

    public boolean isWinner() {
        return isWinner;
    }
}
