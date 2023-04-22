package racingcar.domain;

public class Winner {
    private final Long id;
    private final String name;
    private final Long gameResultId;

    public Winner(Long id, String name, Long gameResultId) {
        this.id = id;
        this.name = name;
        this.gameResultId = gameResultId;
    }

    public static Winner of(String name, Long gameResultId) {
        return new Winner(null, name, gameResultId);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getGameResultId() {
        return gameResultId;
    }
}
