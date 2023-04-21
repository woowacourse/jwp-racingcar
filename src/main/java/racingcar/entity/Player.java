package racingcar.entity;

public class Player {

    private final String name;
    private final int position;
    private final boolean isWinner;

    public Player(final String name, final int position, final boolean isWinner) {
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

    public boolean getIsWinner() {
        return isWinner;
    }

    @Override
    public String toString() {
        return "PlayerSaveDto{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", isWinner=" + isWinner +
                '}';
    }
}
