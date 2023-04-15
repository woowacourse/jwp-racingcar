package racingcar.dao.entity;

public class CarEntity {

    private final int id;
    private final String name;
    private final int position;
    private final Long gameId;
    private final boolean isWin;

    private CarEntity(final Integer id,
                      final String name,
                      final int position,
                      final Long gameId,
                      final boolean isWin) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.gameId = gameId;
        this.isWin = isWin;
    }

    public static CarEntity create(final String name, final int position, final Long gameId, final boolean isWin) {
        return new CarEntity(0, name, position, gameId, isWin);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getGameId() {
        return gameId;
    }

    public boolean getIsWin() {
        return isWin;
    }
}
