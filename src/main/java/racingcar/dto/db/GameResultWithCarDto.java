package racingcar.dto.db;

public final class GameResultWithCarDto {

    private final Long id;
    private final int tryCount;
    private final String name;
    private final int position;
    private final boolean isWinner;

    public GameResultWithCarDto(final Long id, final int tryCount, final String name,
                                final int position, final boolean isWinner) {
        this.id = id;
        this.tryCount = tryCount;
        this.name = name;
        this.position = position;
        this.isWinner = isWinner;
    }

    public Long getId() {
        return id;
    }

    public int getTryCount() {
        return tryCount;
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
