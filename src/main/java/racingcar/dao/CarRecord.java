package racingcar.dao;

public class CarRecord {
    private final String name;
    private final int position;
    private final boolean isWinner;

    public CarRecord(String name, int position, boolean isWinner) {
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
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
