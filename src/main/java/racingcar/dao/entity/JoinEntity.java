package racingcar.dao.entity;

public class JoinEntity {

    private long gameId;
    private String winners;
    private String name;
    private int position;

    public JoinEntity(final long gameId, final String winners, final String name, final int position) {
        this.gameId = gameId;
        this.winners = winners;
        this.name = name;
        this.position = position;
    }

    public long getGameId() {
        return gameId;
    }

    public String getWinners() {
        return winners;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setGameId(final long gameId) {
        this.gameId = gameId;
    }

    public void setWinners(final String winners) {
        this.winners = winners;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPosition(final int position) {
        this.position = position;
    }
}
