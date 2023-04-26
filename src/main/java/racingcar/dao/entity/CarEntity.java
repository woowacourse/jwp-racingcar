package racingcar.dao.entity;

public class CarEntity {

    private final String name;
    private final int position;
    private final boolean winner;
    private final Long racingGameId;

    public CarEntity(String name, int position, boolean isWin, Long gameId) {
        this.name = name;
        this.position = position;
        this.winner = isWin;
        this.racingGameId = gameId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isWinner() {
        return winner;
    }

    public Long getRacingGameId() {
        return racingGameId;
    }
}
