package racingcar.dao;

public class Player {

    private String name;
    private int position;
    private boolean isWinner;

    private Player() {
    }

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

    public void setName(final String name) {
        this.name = name;
    }

    public void setPosition(final int position) {
        this.position = position;
    }

    public void setIsWinner(final boolean winner) {
        isWinner = winner;
    }
}
