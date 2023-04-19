package racingcar.entity;

public class PlayerResult {

    private long id;
    private final String name;
    private final int finalPosition;
    private final Game game;

    public PlayerResult(final String name, final int finalPosition, final Game game) {
        this.name = name;
        this.finalPosition = finalPosition;
        this.game = game;
    }

    public PlayerResult(final long id, final PlayerResult playerResult) {
        this.id = id;
        this.name = playerResult.name;
        this.finalPosition = playerResult.finalPosition;
        this.game = playerResult.game;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFinalPosition() {
        return finalPosition;
    }

    public Game getGame() {
        return game;
    }
}
