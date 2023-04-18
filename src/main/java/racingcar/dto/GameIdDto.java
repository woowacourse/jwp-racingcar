package racingcar.dto;

public class GameIdDto {

    private final int id;

    private GameIdDto(int id) {
        this.id = id;
    }

    public static GameIdDto from(final int id) {
        return new GameIdDto(id);
    }

    public int getId() {
        return id;
    }
}
